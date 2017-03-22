package com.cs.system.service;

import java.util.List;
import java.util.Set;

import com.cs.system.entity.SystemMenu;
import com.cs.system.entity.SystemRole;

public interface MenuService {

	public List<SystemMenu> getMenuListByParentId(int id, Set<SystemRole> roles);
}
