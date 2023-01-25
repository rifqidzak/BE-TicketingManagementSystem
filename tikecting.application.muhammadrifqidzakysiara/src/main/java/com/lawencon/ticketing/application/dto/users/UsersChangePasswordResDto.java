package com.lawencon.ticketing.application.dto.users;

public class UsersChangePasswordResDto {
	private UsersChangePasswordResDataDto data;
	private String message;
	
	public UsersChangePasswordResDataDto getData() {
		return data;
	}
	public void setData(UsersChangePasswordResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
