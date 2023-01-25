package com.lawencon.ticketing.application.dto.files;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FileInsertDataReqDto {
	@Size(max = 5, message = "Message Range 0-5")
	@NotBlank(message = "Extensions Required")
	private String extensions;
	
	@NotBlank(message = "File Required")
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
