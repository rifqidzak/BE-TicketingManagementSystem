package com.lawencon.ticketing.application.dto.tickets;

public class TicketUpdateResDto {
	private String message;
	private TicketUpdateDataResDto data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TicketUpdateDataResDto getData() {
		return data;
	}

	public void setData(TicketUpdateDataResDto data) {
		this.data = data;
	}
}
