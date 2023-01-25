package com.lawencon.ticketing.application.service;

import com.lawencon.ticketing.application.dto.products.ProductDeleteResDto;
import com.lawencon.ticketing.application.dto.products.ProductDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertReqDto;
import com.lawencon.ticketing.application.dto.products.ProductInsertResDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateReqDto;
import com.lawencon.ticketing.application.dto.products.ProductUpdateResDto;
import com.lawencon.ticketing.application.dto.products.ProductsDto;

public interface ProductService {
	ProductInsertResDto insert(final ProductInsertReqDto data);

	ProductUpdateResDto update(final ProductUpdateReqDto data);

	ProductDto getById(final Long id);

	ProductsDto getAll();

	ProductDeleteResDto deleteById(final Long id);
}
