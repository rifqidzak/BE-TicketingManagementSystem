package com.lawencon.ticketing.application.dto.priorities;

public class PrioritiesDataDto {
	private Long id;
	private String prioritiesCode;
	private String prioritiesName;
	private Integer ver;
	

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
	public String getPrioritiesCode() {
		return prioritiesCode;
	}
	public void setPrioritiesCode(String prioritiesCode) {
		this.prioritiesCode = prioritiesCode;
	}
	public String getPrioritiesName() {
		return prioritiesName;
	}
	public void setPrioritiesName(String prioritiesName) {
		this.prioritiesName = prioritiesName;
	}
}
