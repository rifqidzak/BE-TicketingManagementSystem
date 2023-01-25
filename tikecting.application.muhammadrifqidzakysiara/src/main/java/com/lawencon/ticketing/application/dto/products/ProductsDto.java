package com.lawencon.ticketing.application.dto.products;

import java.util.List;

public class ProductsDto {
	private List<ProductsDataDto> data;

	public List<ProductsDataDto> getData() {
		return data;
	}

	public void setData(List<ProductsDataDto> data) {
		this.data = data;
	}
}
