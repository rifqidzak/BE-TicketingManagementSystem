package com.lawencon.ticketing.application.dto.tickets;

import java.util.List;

public class TicketsDto {
	private List<TicketDataDto> data;

	public List<TicketDataDto> getData() {
		return data;
	}

	public void setData(List<TicketDataDto> data) {
		this.data = data;
	}
}
