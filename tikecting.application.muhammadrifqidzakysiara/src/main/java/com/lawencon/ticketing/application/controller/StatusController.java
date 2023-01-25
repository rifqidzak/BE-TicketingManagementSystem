package com.lawencon.ticketing.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.status.StatusDto;
import com.lawencon.ticketing.application.dto.status.StatusesDto;
import com.lawencon.ticketing.application.service.StatusService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("status")
public class StatusController {
	private final StatusService statusService;
	
	StatusController(StatusService statusService){
		this.statusService = statusService;
	}
	
	@GetMapping
	public ResponseEntity<StatusesDto> getAll(){
		final StatusesDto statusesDto = statusService.getAll();
		
		return new ResponseEntity<>(statusesDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<StatusDto> getById(@PathVariable("id") Long id){
		final StatusDto statusDto = statusService.getById(id);
		return new ResponseEntity<>(statusDto, HttpStatus.OK);
	}
}
