package com.lawencon.ticketing.application.dto.companies;

public class CompaniesDataDto {
	private Long id;
	private String companiesCode;
	private String companiesName;
	private Integer ver;
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
