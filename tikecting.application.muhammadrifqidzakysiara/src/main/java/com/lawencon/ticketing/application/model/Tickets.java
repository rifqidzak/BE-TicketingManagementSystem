package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Tickets extends BaseModel {
	
	@Column(name = "title_tickets", length = 30)
	private String titleTickets;
	
	@Column(name = "fill_in_tickets")
	private String fillInTickets;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productId;
	
	@ManyToOne
	@JoinColumn(name = "customers_id")
	private Users customersId;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status statusId;
	
	@ManyToOne
	@JoinColumn(name = "priorities_id")
	private Priorities prioritiesId;
	
	@Column(name = "tickets_code", unique = true, nullable = false, length = 5)
	private String ticketsCode;
	
	public String getTicketsCode() {
		return ticketsCode;
	}
	public void setTicketsCode(String ticketsCode) {
		this.ticketsCode = ticketsCode;
	}
	public Priorities getPrioritiesId() {
		return prioritiesId;
	}
	public void setPrioritiesId(Priorities prioritiesId) {
		this.prioritiesId = prioritiesId;
	}
	public String getTitleTickets() {
		return titleTickets;
	}
	public void setTitleTickets(String titleTickets) {
		this.titleTickets = titleTickets;
	}
	public String getFillInTickets() {
		return fillInTickets;
	}
	public void setFillInTickets(String fillInTickets) {
		this.fillInTickets = fillInTickets;
	}
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
	public Status getStatusId() {
		return statusId;
	}
	public void setStatusId(Status statusId) {
		this.statusId = statusId;
	}
	
	
}
