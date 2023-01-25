package com.lawencon.ticketing.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.attachmenttickets.AttachmentsTicketsDto;
import com.lawencon.ticketing.application.service.AttachmentTicketsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("attachment-tickets")
public class AttachmentTicketsController {
	private final AttachmentTicketsService attachmentTicketsService;

	public AttachmentTicketsController(AttachmentTicketsService attachmentTicketsService) {
		this.attachmentTicketsService = attachmentTicketsService;
	}
	
	@GetMapping("{ticket-id}")
	public ResponseEntity<AttachmentsTicketsDto> getAllByTicketId(@PathVariable("ticket-id") Long ticketId){
		final AttachmentsTicketsDto attachmentsTicketsDto = attachmentTicketsService.getByTicketsId(ticketId);
		return new ResponseEntity<>(attachmentsTicketsDto, HttpStatus.OK);
	}
	
}
