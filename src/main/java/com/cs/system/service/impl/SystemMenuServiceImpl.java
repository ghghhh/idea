package com.cs.system.service.impl;

import java.util.List;
import java.util.Set;
import com.cs.system.dao.SystemMenuDao;
import com.cs.system.entity.SystemMenu;
import com.cs.system.entity.SystemRole;
import com.cs.system.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SystemMenuServiceImpl implements SystemMenuService {

	@Autowired
	private SystemMenuDao menuDao;
	@Override
	public List<SystemMenu> getMenuListByParentId(int id, Set<SystemRole> roles) {
		
		return menuDao.getMenuListByFatherId(id,roles); 
	}
}
