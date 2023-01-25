package com.lawencon.ticketing.application.dto.companies;

import java.util.List;

public class CompaniesDto {
	private List <CompaniesDataDto> data;

	public List<CompaniesDataDto> getData() {
		return data;
	}

	public void setData(List<CompaniesDataDto> data) {
		this.data = data;
	}

}
