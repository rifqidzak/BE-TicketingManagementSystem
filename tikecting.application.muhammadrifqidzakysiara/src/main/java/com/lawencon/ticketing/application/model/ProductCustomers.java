package com.lawencon.ticketing.application.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_customers")
public class ProductCustomers extends BaseModel {
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	
	@ManyToOne
	@JoinColumn(name = "customers_id")
	private Users customersId;
	
	public Product getProductId() {
		return productId;
	}
	public void setProductId(Product productId) {
		this.productId = productId;
	}
	public Users getCustomersId() {
		return customersId;
	}
	public void setCustomersId(Users customersId) {
		this.customersId = customersId;
	}
	

	
}
