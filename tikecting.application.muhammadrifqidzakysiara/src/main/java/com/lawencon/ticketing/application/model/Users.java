package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users extends BaseModel{
	
	@Column(name = "full_name", length = 20)
	private String fullName;
	
	@Column(name = "email", unique = true, nullable = true, length = 40)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_users_id")
	private RoleUsers roleUserId;
	
	@ManyToOne
	@JoinColumn(name = "files_id")
	private Files filesId;
	
	@ManyToOne
	@JoinColumn(name = "pic_id")
	private Users picId;
	
	@ManyToOne
	@JoinColumn(name = "companies_id")
	private Companies companiesId;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RoleUsers getRoleUserId() {
		return roleUserId;
	}
	public void setRoleUserId(RoleUsers roleUserId) {
		this.roleUserId = roleUserId;
	}
	public Files getFilesId() {
		return filesId;
	}
	public void setFilesId(Files filesId) {
		this.filesId = filesId;
	}
	public Users getPicId() {
		return picId;
	}
	public void setPicId(Users picId) {
		this.picId = picId;
	}
	public Companies getCompaniesId() {
		return companiesId;
	}
	public void setCompaniesId(Companies companiesId) {
		this.companiesId = companiesId;
	}
	
	
}
