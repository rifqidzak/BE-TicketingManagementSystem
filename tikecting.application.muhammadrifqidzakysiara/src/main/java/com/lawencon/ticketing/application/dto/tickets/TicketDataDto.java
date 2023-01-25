	package com.lawencon.ticketing.application.dto.tickets;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDataDto {
	private Long id;
	private String ticketCode;
	private String title;
	private String fillInTickets;
	private String customerName;
	private String prioritiesName;
	private String statusName;
	private String statusCode;
	private LocalDateTime createdAt;
	private Integer ver;
	private List<Long> fileId;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public List<Long> getFileId() {
		return fileId;
	}

	public void setFileId(List<Long> fileId) {
		this.fileId = fileId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}

	public String getTicketCode() {
		return ticketCode;
	}

	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFillInTickets() {
		return fillInTickets;
	}

	public void setFillInTickets(String fillInTickets) {
		this.fillInTickets = fillInTickets;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPrioritiesName() {
		return prioritiesName;
	}

	public void setPrioritiesName(String prioritiesName) {
		this.prioritiesName = prioritiesName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}
