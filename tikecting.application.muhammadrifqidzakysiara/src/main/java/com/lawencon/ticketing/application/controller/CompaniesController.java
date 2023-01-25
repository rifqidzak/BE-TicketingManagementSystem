package com.lawencon.ticketing.application.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.companies.CompaniesDeleteResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertReqDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesInsertResDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateReqDto;
import com.lawencon.ticketing.application.dto.companies.CompaniesUpdateResDto;
import com.lawencon.ticketing.application.dto.companies.CompanyDto;
import com.lawencon.ticketing.application.service.CompaniesService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("companies")
public class CompaniesController {
	private final CompaniesService companiesService;

	public CompaniesController(CompaniesService companiesService) {
		this.companiesService = companiesService;
	}

	@GetMapping
	public ResponseEntity<CompaniesDto> getAll() {
		final CompaniesDto companiesDto = companiesService.getAll();

		return new ResponseEntity<>(companiesDto, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<CompanyDto> getById(@PathVariable("id") Long id) {
		final CompanyDto companyDto = companiesService.getById(id);
		return new ResponseEntity<>(companyDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CompaniesInsertResDto> insert(@RequestBody @Valid CompaniesInsertReqDto data) {
		final CompaniesInsertResDto companiesInsertResDto = companiesService.insert(data);
		return new ResponseEntity<>(companiesInsertResDto, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<CompaniesUpdateResDto> updated(@RequestBody @Valid CompaniesUpdateReqDto data) {
		final CompaniesUpdateResDto companiesUpdateResDto = companiesService.update(data);
		return new ResponseEntity<>(companiesUpdateResDto, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<CompaniesDeleteResDto> delete(@PathVariable("id") Long id) {
		final CompaniesDeleteResDto companiesDeleteDto = companiesService.deleteById(id);
		return new ResponseEntity<>(companiesDeleteDto, HttpStatus.OK);
	}
}
