package com.lawencon.ticketing.application.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.tickets.TicketsDto;
import com.lawencon.ticketing.application.dto.tickets.TicketDto;
import com.lawencon.ticketing.application.dto.tickets.TicketInsertResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketUpdateResDto;
import com.lawencon.ticketing.application.dto.tickets.TicketsInsertDataReqDto;
import com.lawencon.ticketing.application.service.TicketsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("tickets")
public class TicketsController {
	private TicketsService ticketsService;

	public TicketsController(TicketsService ticketsService) {
		this.ticketsService = ticketsService;
	}
	
	@PostMapping
	public ResponseEntity<TicketInsertResDto> insert(@RequestBody @Valid TicketsInsertDataReqDto data){
		final TicketInsertResDto ticketInsertResDto = ticketsService.insert(data);
		return new ResponseEntity<>(ticketInsertResDto, HttpStatus.CREATED);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<TicketUpdateResDto> update(@PathVariable("id") Long id){
		final TicketUpdateResDto ticketUpdateResDto = ticketsService.update(id);
		return new ResponseEntity<>(ticketUpdateResDto, HttpStatus.OK);
	}
	
	@GetMapping("allcs/{id}")
	public ResponseEntity<TicketsDto> getAllCustomersSide(@PathVariable("id")Long id){
		final TicketsDto ticketDto = ticketsService.getAllCustomersSides(id);
		return new ResponseEntity<>(ticketDto, HttpStatus.OK);
	}
	
	@GetMapping("allps/{id}")
	public ResponseEntity<TicketsDto> getAllPicSides(@PathVariable("id")Long id){
		final TicketsDto ticketDto = ticketsService.getAllPicSides(id);
		return new ResponseEntity<>(ticketDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TicketDto> getById(@PathVariable("id")Long id){
		final TicketDto ticketDto = ticketsService.getById(id);
		return new ResponseEntity<>(ticketDto, HttpStatus.OK);
	}
	
	
	
}
