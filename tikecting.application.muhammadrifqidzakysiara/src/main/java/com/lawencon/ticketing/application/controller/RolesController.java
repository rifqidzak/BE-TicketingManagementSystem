package com.lawencon.ticketing.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.roles.RoleDto;
import com.lawencon.ticketing.application.dto.roles.RolesDto;
import com.lawencon.ticketing.application.service.RoleService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("roles")
public class RolesController {
	private final RoleService roleService;

	public RolesController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping
	public ResponseEntity<RolesDto> getAll(){
		final RolesDto rolesDto = roleService.getAll();
		return new ResponseEntity<>(rolesDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RoleDto> getById(@PathVariable("id") Long id){
		final RoleDto roleDto = roleService.getById(id);
		return new ResponseEntity<>(roleDto, HttpStatus.OK);
	}
	
}
