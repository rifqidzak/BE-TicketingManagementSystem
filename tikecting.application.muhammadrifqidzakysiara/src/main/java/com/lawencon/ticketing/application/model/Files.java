package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class Files extends BaseModel {
	@Column(name = "extensions", length = 5)
	private String extensions;
	
	@Column(name = "files_name", nullable = false)
	private String fileName;
	
	public String getExtensions() {
		return extensions;
	}
	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
