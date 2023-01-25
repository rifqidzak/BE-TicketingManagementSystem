package com.lawencon.ticketing.application.dto.users;

public class UsersInsertResDto {
	private UsersInsertResDataDto data;
	private String message;

	

	public UsersInsertResDataDto getData() {
		return data;
	}

	public void setData(UsersInsertResDataDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
