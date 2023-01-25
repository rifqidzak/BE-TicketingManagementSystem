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

import com.lawencon.ticketing.application.dto.products.ProductDeleteResDto;
import com.lawencon.ticketing.application.dto.products.ProductDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertReqDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertResDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateReqDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateResDto;
import com.lawencon.ticketing.application.dto.products.ProductsDto;
import com.lawencon.ticketing.application.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("products")
public class ProductsController {
	private final ProductService productService;
	
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<ProductsDto> getAll(){
		final ProductsDto productsDto = productService.getAll();
		
		return new ResponseEntity<>(productsDto, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProductInsertResDto> insert(@RequestBody @Valid ProductInsertReqDto data){
		final ProductInsertResDto productInsertResDto = productService.insert(data);
		return new ResponseEntity<>(productInsertResDto, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ProductUpdateResDto> updated(@RequestBody @Valid ProductUpdateReqDto data){
		final ProductUpdateResDto productUpdateResDto = productService.update(data);
		return new ResponseEntity<>(productUpdateResDto, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<ProductDeleteResDto> delete(@PathVariable("id") Long id){
		final ProductDeleteResDto productDeleteResDto = productService.deleteById(id);
		return new ResponseEntity<>(productDeleteResDto, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProductDto> getById(@PathVariable("id") Long id){
		final ProductDto productDto = productService.getById(id);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
}
