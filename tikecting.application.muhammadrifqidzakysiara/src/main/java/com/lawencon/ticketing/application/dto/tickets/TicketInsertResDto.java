package com.lawencon.ticketing.application.dto.tickets;

public class TicketInsertResDto {
	private String message;
	private TicketInsertDataResDto data;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TicketInsertDataResDto getData() {
		return data;
	}
	public void setData(TicketInsertDataResDto data) {
		this.data = data;
	}
}
