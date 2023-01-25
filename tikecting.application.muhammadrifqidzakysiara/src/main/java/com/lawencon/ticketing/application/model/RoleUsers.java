package com.lawencon.ticketing.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role_users")
public class RoleUsers extends BaseModel{
	
	@Column(name = "role_users_code", unique = true , nullable = false, length = 8 )
	private String roleUserCode;
	
	@Column(name = "role_users_name", length = 15)
	private String roleUserName;

	public String getRoleUserCode() {
		return roleUserCode;
	}
	public void setRoleUserCode(String roleUserCode) {
		this.roleUserCode = roleUserCode;
	}
	public String getRoleUserName() {
		return roleUserName;
	}
	public void setRoleUserName(String roleUserName) {
		this.roleUserName = roleUserName;
	}
}
