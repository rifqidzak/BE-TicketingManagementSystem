package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "priorities")
public class Priorities extends BaseModel {
	@Column(name = "priorities_code", unique = true, nullable = false, length = 8)
	private String prioritiesCode;
	
	@Column(name = "priorities_name", length = 20)
	private String prioritiesName;

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
