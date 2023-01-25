package com.lawencon.ticketing.application.dto.companies;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CompaniesUpdateReqDto {
	@Size(max = 20, message = "Code Length 0 Between 20")
	private String companiesName;

	@NotNull(message = "ID Required")
	private Long id;
	
	@NotNull(message ="Version Required")
	private Integer ver;
	
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCompaniesName() {
		return companiesName;
	}

	public void setCompaniesName(String companiesName) {
		this.companiesName = companiesName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVer() {
		return ver;
	}

	public void setVer(Integer ver) {
		this.ver = ver;
	}
}
