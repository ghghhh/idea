package com.cs.system.dao;

import com.cs.system.entity.SystemPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
@Mapper
public interface SystemPermissionDao {
    int addPermission(SystemPermission permission);
    int delPermission(int id);
    int updatePermission(SystemPermission permission);
    SystemPermission getPermissionById(int id);
    List<SystemPermission> getPermissionListByRoleId(int rid);
}
