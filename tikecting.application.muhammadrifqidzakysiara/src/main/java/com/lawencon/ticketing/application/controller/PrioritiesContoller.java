package com.lawencon.ticketing.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.priorities.PrioritiesDto;
import com.lawencon.ticketing.application.dto.priorities.PriorityDto;
import com.lawencon.ticketing.application.service.PrioritiesService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("priorities")
public class PrioritiesContoller {
	private final PrioritiesService prioritiesService;

	public PrioritiesContoller(PrioritiesService prioritiesService) {
		this.prioritiesService = prioritiesService;
	}

	@GetMapping
	public ResponseEntity<PrioritiesDto> getAll() {
		final PrioritiesDto prioritiesDto = prioritiesService.getAll();

		return new ResponseEntity<>(prioritiesDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<PriorityDto> getById(@PathVariable("id") Long id) {
		final PriorityDto priorityDto = prioritiesService.getById(id);
		return new ResponseEntity<>(priorityDto, HttpStatus.OK);
	}
}
