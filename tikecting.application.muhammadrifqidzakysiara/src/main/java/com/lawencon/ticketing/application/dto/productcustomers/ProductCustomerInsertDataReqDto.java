package com.lawencon.ticketing.application.dto.productcustomers;

public class ProductCustomerInsertDataReqDto {
	private Long productId;

	private Long customerId;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
