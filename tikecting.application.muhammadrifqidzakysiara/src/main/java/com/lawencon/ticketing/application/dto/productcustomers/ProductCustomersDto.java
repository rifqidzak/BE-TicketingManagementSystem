package com.lawencon.ticketing.application.dto.productcustomers;

import java.util.List;

public class ProductCustomersDto {
	private List<ProductCustomerDataDto>data;

	public List<ProductCustomerDataDto> getData() {
		return data;
	}

	public void setData(List<ProductCustomerDataDto> data) {
		this.data = data;
	}
}
