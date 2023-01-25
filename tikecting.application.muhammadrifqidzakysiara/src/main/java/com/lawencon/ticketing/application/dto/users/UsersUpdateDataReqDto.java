package com.lawencon.ticketing.application.dto.users;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersUpdateDataReqDto {
	@NotNull(message = "User Not Found")
	private Long id ;
	
	@Size(max = 20)
	private String fullName;
	
	private Long roleUsersId;
	
	private Long picId;
	
	private Long companiesId;
	
	private String photo;
	
	private String extensionPhoto;
	
	private Integer ver;
	
	private Boolean isActive;
	
	public String getExtensionPhoto() {
		return extensionPhoto;
	}
	public void setExtensionPhoto(String extensionPhoto) {
		this.extensionPhoto = extensionPhoto;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Long getRoleUsersId() {
		return roleUsersId;
	}
	public void setRoleUsersId(Long roleUsersId) {
		this.roleUsersId = roleUsersId;
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
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	
	
}
