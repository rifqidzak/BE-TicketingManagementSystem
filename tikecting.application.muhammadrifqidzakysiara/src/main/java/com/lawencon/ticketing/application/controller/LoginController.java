package com.lawencon.ticketing.application.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.login.LoginReqDto;
import com.lawencon.ticketing.application.dto.login.LoginResDto;
import com.lawencon.ticketing.application.model.Users;
import com.lawencon.ticketing.application.service.JwtService;
import com.lawencon.ticketing.application.service.UsersService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("login")
public class LoginController {
	private final AuthenticationManager authenticationManager;
	private final UsersService usersService;
	private final JwtService jwtService;
	public LoginController(AuthenticationManager authenticationManager, UsersService usersService, JwtService jwtService){
		this.authenticationManager = authenticationManager;
		this.usersService = usersService;
		this.jwtService = jwtService;
	}
	@PostMapping
	public ResponseEntity<LoginResDto> Login(@RequestBody LoginReqDto loginReqDto) {
		final Authentication object = new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword());
		authenticationManager.authenticate(object);
		final Optional<Users> userOptional = usersService.getUserByEmail(loginReqDto.getEmail());
		final Map<String, Object>claims = new HashMap<>();
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		claims.put("exp", cal.getTime());
		claims.put("id", userOptional.get().getId());
		claims.put("rc", userOptional.get().getRoleUserId().getRoleUserCode());
		
		final LoginResDto loginResDto = new LoginResDto();
		loginResDto.setId(userOptional.get().getId());
		loginResDto.setFullName(userOptional.get().getFullName());
		loginResDto.setRoleCode(userOptional.get().getRoleUserId().getRoleUserCode());
		if(userOptional.get().getFilesId() != null) {
			loginResDto.setPhotoId(userOptional.get().getFilesId().getId());			
		}
		loginResDto.setVer(userOptional.get().getVer());
		loginResDto.setToken(jwtService.generateJwt(claims));
		loginResDto.setRoleName(userOptional.get().getRoleUserId().getRoleUserName());
		loginResDto.setCompanyId(userOptional.get().getCompaniesId().getId());
		
		return new ResponseEntity<LoginResDto>(loginResDto, HttpStatus.OK);
	}
}
