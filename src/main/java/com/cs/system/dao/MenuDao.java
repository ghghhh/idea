package com.cs.system.dao;

import java.util.List;
import java.util.Set;

import com.cs.system.entity.SystemMenu;
import com.cs.system.entity.SystemRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface MenuDao {

	 public List<SystemMenu> getMenuListByFatherId(@Param("id") int id, @Param("roles") Set<SystemRole> roles);
}
