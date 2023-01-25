package com.lawencon.ticketing.application.dto.users;

public class UsersUpdateResDto {
	private UsersUpdateDataResDto data;
	private String message;
	public UsersUpdateDataResDto getData() {
		return data;
	}

	public void setData(UsersUpdateDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
