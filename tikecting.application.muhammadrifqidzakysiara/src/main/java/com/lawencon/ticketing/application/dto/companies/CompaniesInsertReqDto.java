package com.lawencon.ticketing.application.dto.companies;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CompaniesInsertReqDto {
	
	@NotBlank(message = "Code Required")
	@Size(max = 8 , message = "Code Length 0 Between 8")
	private String companiesCode;
	
	@Size(max = 100, message = "Name Length 0 Between 20")
	private String companiesName;
	
	public String getCompaniesCode() {
		return companiesCode;
	}
	public void setCompaniesCode(String companiesCode) {
		this.companiesCode = companiesCode;
	}
	public String getCompaniesName() {
		return companiesName;
	}
	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}
}
