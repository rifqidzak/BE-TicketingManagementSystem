package com.lawencon.ticketing.application.dto.productcustomers;

public class ProductCustomerInsertResDto {
	private ProductCustomerInsertDataResDto data;
	private String message;

	
	public ProductCustomerInsertDataResDto getData() {
		return data;
	}

	public void setData(ProductCustomerInsertDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
