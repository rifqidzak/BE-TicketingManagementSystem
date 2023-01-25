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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.users.UserDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordReqDataDto;
import com.lawencon.ticketing.application.dto.users.UsersChangePasswordResDto;
import com.lawencon.ticketing.application.dto.users.UsersDeleteResDto;
import com.lawencon.ticketing.application.dto.users.UsersDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertReqDataDto;
import com.lawencon.ticketing.application.dto.users.UsersInsertResDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateDataReqDto;
import com.lawencon.ticketing.application.dto.users.UsersUpdateResDto;
import com.lawencon.ticketing.application.service.UsersService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UsersController {
	private UsersService usersService;

	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GetMapping("role")
	public ResponseEntity<UsersDto> getByRole(@RequestParam("code") String code){
		final UsersDto usersByRoleDto = usersService.getUsersByRole(code);
		return new ResponseEntity<>(usersByRoleDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<UsersDto> getAll(){
		final UsersDto usersDto = usersService.getAll();
		return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}
	
	@GetMapping("pic-customers/{id}")
	public ResponseEntity<UsersDto> getPicCustomers(@PathVariable ("id") Long id){
		final UsersDto usersDto = usersService.getPicCustomers(id);
		return new ResponseEntity<>(usersDto, HttpStatus.OK);
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id){
		final UserDto userDto = usersService.getById(id);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
//	@PreAuthorize("hasAuthority('RS01')")
	@PostMapping
	public ResponseEntity<UsersInsertResDto> insert(@RequestBody @Valid UsersInsertReqDataDto data){
		final UsersInsertResDto usersInsertResDto = usersService.insert(data);
		return new ResponseEntity<>(usersInsertResDto, HttpStatus.CREATED);
	}
	
//	@PreAuthorize("hasAuthority('RS01')")
	@PutMapping
	public ResponseEntity<UsersUpdateResDto> update(@RequestBody @Valid UsersUpdateDataReqDto data){
		final UsersUpdateResDto usersUpdateResDto = usersService.update(data);
		return new ResponseEntity<>(usersUpdateResDto, HttpStatus.OK);
	}
	
	@PutMapping("/change-password")
	public ResponseEntity<UsersChangePasswordResDto> changePassword(@RequestBody @Valid UsersChangePasswordReqDataDto data){
		final UsersChangePasswordResDto usersChangePasswordResDto = usersService.changePassword(data);
		return new ResponseEntity<>(usersChangePasswordResDto, HttpStatus.OK);
	}	
	
	@DeleteMapping("{id}")
	public ResponseEntity<UsersDeleteResDto> delete(@PathVariable("id") Long id){
		final UsersDeleteResDto deleteResDto = usersService.deleteById(id);
		return new ResponseEntity<>(deleteResDto, HttpStatus.OK);
	}
}
