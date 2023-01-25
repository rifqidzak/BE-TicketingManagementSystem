package com.lawencon.ticketing.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.ticketing.application.dto.exception.ExceptionDto;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDto<List<String>>> handleValidation(MethodArgumentNotValidException ex) {
		
		final ExceptionDto<List<String>> exceptionDto = new ExceptionDto<List<String>>();
		final List<String> errors = new ArrayList<>();

		ex.getBindingResult().getAllErrors().forEach(e -> {
			errors.add(e.getDefaultMessage());
		});
		
		exceptionDto.setMessage(errors);
		return new ResponseEntity <>(exceptionDto,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ObjectOptimisticLockingFailureException.class)
	public ResponseEntity<ExceptionDto<String>> handleInvalidVersion(ObjectOptimisticLockingFailureException ox){
		
		
		final ExceptionDto<String> exceptionDto = new ExceptionDto<>();
		exceptionDto.setMessage("Invalid Version!");
		
		return new ResponseEntity <>(exceptionDto,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionDto<String>> badCredentialsException(BadCredentialsException be){
		
		
		final ExceptionDto<String> exceptionDto = new ExceptionDto<>();
		exceptionDto.setMessage("Email/Password Wrong!");
		
		return new ResponseEntity <>(exceptionDto,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionDto<String>> runtimeException(RuntimeException re){
		
		
		final ExceptionDto<String> exceptionDto = new ExceptionDto<>();
		exceptionDto.setMessage(re.getMessage());
		
		return new ResponseEntity <>(exceptionDto,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(NoResultException.class)
	public ResponseEntity<ExceptionDto<String>> noResultException(NoResultException re){
		
		final ExceptionDto<String> exceptionDto = new ExceptionDto<>();
		exceptionDto.setMessage("Users/Password Wrong!");
		
		return new ResponseEntity <>(exceptionDto,HttpStatus.BAD_REQUEST);
		
	}
}
