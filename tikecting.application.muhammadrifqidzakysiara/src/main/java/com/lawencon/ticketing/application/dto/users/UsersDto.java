package com.lawencon.ticketing.application.dto.users;

import java.util.List;

public class UsersDto {
	private List<UsersDataDto> data;

	public List<UsersDataDto> getData() {
		return data;
	}

	public void setData(List<UsersDataDto> data) {
		this.data = data;
	}
}
