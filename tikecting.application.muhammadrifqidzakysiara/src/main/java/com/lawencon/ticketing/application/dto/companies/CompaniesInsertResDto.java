package com.lawencon.ticketing.application.dto.companies;

public class CompaniesInsertResDto {
	private CompaniesInsertDataResDto data;
	private String message;
	public CompaniesInsertDataResDto getData() {
		return data;
	}
	public void setData(CompaniesInsertDataResDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
