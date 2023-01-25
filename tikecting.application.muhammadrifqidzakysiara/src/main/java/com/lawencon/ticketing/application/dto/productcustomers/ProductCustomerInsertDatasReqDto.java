package com.lawencon.ticketing.application.dto.productcustomers;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class ProductCustomerInsertDatasReqDto {
	@NotEmpty(message = "Product Customer Required")
	private List<ProductCustomerInsertDataReqDto> productCustomerInsertDataReqDtos;

	public List<ProductCustomerInsertDataReqDto> getProductCustomerInsertDataReqDtos() {
		return productCustomerInsertDataReqDtos;
	}

	public void setProductCustomerInsertDataReqDtos(
			List<ProductCustomerInsertDataReqDto> productCustomerInsertDataReqDtos) {
		this.productCustomerInsertDataReqDtos = productCustomerInsertDataReqDtos;
	}


}
