package com.lawencon.ticketing.application.dto.companies;

public class CompaniesUpdateResDto {
	private CompaniesUpdateDataResDto data;
	private String message;

	public CompaniesUpdateDataResDto getData() {
		return data;
	}

	public void setData(CompaniesUpdateDataResDto data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
