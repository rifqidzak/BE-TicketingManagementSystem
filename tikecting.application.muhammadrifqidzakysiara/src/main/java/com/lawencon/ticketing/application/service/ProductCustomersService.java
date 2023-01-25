package com.lawencon.ticketing.application.service;


import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerDeleteDataResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertDatasReqDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomerInsertResDto;
import com.lawencon.ticketing.application.dto.productcustomers.ProductCustomersDto;

public interface ProductCustomersService {
	ProductCustomersDto getAll();

	ProductCustomerInsertResDto insert(ProductCustomerInsertDatasReqDto data);

	ProductCustomerDeleteDataResDto deleteById(final Long id);

	ProductCustomersDto getByCustomers(final Long id);
}
