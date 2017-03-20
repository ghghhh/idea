package com.cs.system.service;

import com.cs.system.entity.SystemPermission;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
public interface SystemPermissionService {
    boolean addPermission(SystemPermission permission);
    boolean delPermission(int id);
    boolean updatePermission(SystemPermission permission);
    SystemPermission getPermissionById(int id);
    List<SystemPermission> getPermissionListByRoleId(int rid);
}
