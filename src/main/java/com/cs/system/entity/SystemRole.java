package com.cs.system.entity;

import javax.validation.constraints.NotNull;

import com.cs.common.baseEntity.BaseRequestDTO;
import org.hibernate.validator.constraints.NotEmpty;
public class SystemRole extends BaseRequestDTO{

	/**
	 * 
	 */
	private Integer id;
	@NotEmpty(message="1000:角色名不能为空")
	private String roleName;
	private String cnName;
	@NotNull(message="1000:角色父id不能为空")
	private Integer parentId;
	private String isParent;
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
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}


