package com.cs.system.service;

import com.cs.system.entity.SystemRole;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
public interface RoleServive {
    SystemRole getRoleById(int id);
    boolean createRole(SystemRole role);
    boolean delRole(int id);
    boolean updateRole(SystemRole role);
    List<SystemRole> getRoleListByUserId(long uid);
}
