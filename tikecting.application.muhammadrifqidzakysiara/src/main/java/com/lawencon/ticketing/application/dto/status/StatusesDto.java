package com.lawencon.ticketing.application.dto.status;

import java.util.List;

public class StatusesDto {
	private List <StatusDataDto> data;

	public List<StatusDataDto> getData() {
		return data;
	}

	public void setData(List<StatusDataDto> data) {
		this.data = data;
	}
}
