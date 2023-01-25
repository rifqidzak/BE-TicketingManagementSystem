package com.lawencon.ticketing.application.dto.users;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersInsertReqDataDto {
	@NotBlank(message = "Full Name Required")
	@Size(max = 20, message = "Full Name Range 0-20")
	private String fullName;

	@NotBlank(message = "Email Required")
	@Size(max = 40, message = "Full Name Range 0-40")
	private String email;

	@NotNull(message = "RoleUser Required")
	private Long roleUserId;

	private Long picId;

	private Long companiesId;
	
	private String photo;
	
	private String extensionPhoto;
	
	

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getExtensionPhoto() {
		return extensionPhoto;
	}

	public void setExtensionPhoto(String extensionPhoto) {
		this.extensionPhoto = extensionPhoto;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleUserId() {
		return roleUserId;
	}

	public void setRoleUserId(Long roleUserId) {
		this.roleUserId = roleUserId;
	}

	public Long getPicId() {
		return picId;
	}

	public void setPicId(Long picId) {
		this.picId = picId;
	}

	public Long getCompaniesId() {
		return companiesId;
	}

	public void setCompaniesId(Long companiesId) {
		this.companiesId = companiesId;
	}
}
