package com.lawencon.ticketing.application.dto.products;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductInsertReqDto {
	@NotNull(message = "Code Required")
	@Size(max = 8, message = "Code Length 0 Between 8")
	private String productCode;
	
	@NotNull(message = "Name Required")
	@Size(max = 20, message = "Code Length 0 Between 20")
	private String productName;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
