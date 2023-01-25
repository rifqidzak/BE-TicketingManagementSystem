package com.lawencon.ticketing.application.dto.users;

public class UsersDataDto {
	private String fullName;
	private String roleName;
	private String email;
	private String picName;
	private String companiesName;
	private Long photoId;
	private Long id;
	private Integer ver;
	private Boolean isActive;
	private Long roleId;
	private Long picId;
	private Long companiesId;
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhotoId() {
		return photoId;
	}
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}
	public Integer getVer() {
		return ver;
	}
	public void setVer(Integer ver) {
		this.ver = ver;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
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
}
