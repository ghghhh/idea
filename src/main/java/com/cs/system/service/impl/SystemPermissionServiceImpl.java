package com.cs.system.service.impl;

import com.cs.system.dao.SystemPermissionDao;
import com.cs.system.entity.SystemPermission;
import com.cs.system.service.SystemPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
@Service
public class SystemPermissionServiceImpl implements SystemPermissionService{
    @Autowired
    private SystemPermissionDao systemPermissionDao;
    @Override
    public boolean addPermission(SystemPermission permission) {
       int i= systemPermissionDao.addPermission(permission);
        return i>0;
    }

    @Override
    public boolean delPermission(int id) {
        int i=systemPermissionDao.delPermission(id);
        return i>0;
    }

    @Override
    public boolean updatePermission(SystemPermission permission) {
        int i=systemPermissionDao.updatePermission(permission);
        return i>0;
    }

    @Override
    public SystemPermission getPermissionById(int id) {

        return systemPermissionDao.getPermissionById(id);
    }

    @Override
    public List<SystemPermission> getPermissionListByRoleId(int rid) {
        return systemPermissionDao.getPermissionListByRoleId(rid);
    }
}
