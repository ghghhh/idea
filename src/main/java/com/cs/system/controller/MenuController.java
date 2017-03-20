package com.cs.system.controller;

import com.cs.common.baseEntity.ReturnObject;
import com.cs.system.entity.SystemMenu;
import com.cs.system.entity.SystemRole;
import com.cs.system.entity.SystemUser;
import com.cs.system.service.SystemMenuService;
import com.cs.system.service.SystemRoleServive;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;
import java.util.*;

@RestController
public class MenuController {

	@Autowired
	private SystemMenuService menuService;
	@Autowired
	private SystemRoleServive systemRoleServive;
	@RequestMapping(value="/menu/load",method=RequestMethod.POST)
	public Object loadMenu(Integer id){
		if(id==null){
			id=0;
		}
		SystemUser user=(SystemUser)SecurityUtils.getSubject().getPrincipal();
		List<SystemRole> listRole=systemRoleServive.getRoleListByUserId(user.getId());
		Set<SystemRole> setRole=new HashSet<>();
		listRole.forEach(r->setRole.add(r));
		List<SystemMenu>  listMenu=menuService.getMenuListByParentId(id,setRole);
		return listMenu;
	}
	public Object getMenuList(Integer id){
		
		return null;
	}
	@RequestMapping("/menu/update")
	public Object updateMenu(SystemMenu myMenu){
		ReturnObject o=new ReturnObject("200", "更新菜单成功");
		return o;
	}
}
