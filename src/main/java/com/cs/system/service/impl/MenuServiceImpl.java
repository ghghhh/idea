package com.cs.system.service.impl;

import java.util.List;
import java.util.Set;
import com.cs.system.dao.MenuDao;
import com.cs.system.entity.SystemMenu;
import com.cs.system.entity.SystemRole;
import com.cs.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
@Service
@Primary
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Override
	public List<SystemMenu> getMenuListByParentId(int id, Set<SystemRole> roles) {
		
		return menuDao.getMenuListByFatherId(id,roles); 
	}
}
