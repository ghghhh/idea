package com.cs.system.entity;

import javax.validation.constraints.NotNull;

import com.cs.common.baseEntity.BaseRequestDTO;
import org.hibernate.validator.constraints.NotEmpty;
public class SystemRole extends BaseRequestDTO{

	/**
	 * 
	 */
	private Integer id;
	private String roleName;
	private String cnName;
	private String enabled;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}


