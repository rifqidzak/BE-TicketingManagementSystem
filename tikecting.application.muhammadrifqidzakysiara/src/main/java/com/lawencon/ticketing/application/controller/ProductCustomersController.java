package com.lawencon.ticketing.application.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerDeleteDataResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertDatasReqDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomersDto;
import com.lawencon.ticketing.application.service.ProductCustomersService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("product-customers")
public class ProductCustomersController {

	private final ProductCustomersService productCustomersService;

	public ProductCustomersController(ProductCustomersService productCustomersService) {
		this.productCustomersService = productCustomersService;
	}
	
	@GetMapping
	public ResponseEntity<ProductCustomersDto> getAll(){
		final ProductCustomersDto productCustomersDto = productCustomersService.getAll();
		return new ResponseEntity<>(productCustomersDto, HttpStatus.OK);
	}
	
	@GetMapping("/cs/{customer-id}")
	public ResponseEntity<ProductCustomersDto> getByCustomers(@PathVariable ("customer-id") Long id){
		final ProductCustomersDto productCustomersDto = productCustomersService.getByCustomers(id);
		return new ResponseEntity<>(productCustomersDto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProductCustomerInsertResDto> insert(@RequestBody @Valid ProductCustomerInsertDatasReqDto data) {
		final ProductCustomerInsertResDto productCustomerInsertResDto = productCustomersService.insert(data);
		return new ResponseEntity<>(productCustomerInsertResDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ProductCustomerDeleteDataResDto> delete(@PathVariable("id") Long id){
		final ProductCustomerDeleteDataResDto productCustomerDeleteDataResDto = productCustomersService.deleteById(id);
		return new ResponseEntity<>(productCustomerDeleteDataResDto, HttpStatus.OK);
	}
	
}
