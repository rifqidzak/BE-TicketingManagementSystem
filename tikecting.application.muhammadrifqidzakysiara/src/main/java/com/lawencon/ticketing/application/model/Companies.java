package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Companies extends BaseModel{
	@Column(name = "companies_code", unique = true, nullable = false, length = 8)
	private String companiesCode;
	
	@Column(name = "companies_name", length = 20)
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
