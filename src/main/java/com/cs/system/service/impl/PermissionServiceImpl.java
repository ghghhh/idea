package com.cs.system.service.impl;

import com.cs.system.dao.PermissionDao;
import com.cs.system.entity.SystemPermission;
import com.cs.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
   
    @Override
    public boolean addPermission(SystemPermission permission) {
       int i= permissionDao.addPermission(permission);
        return i>0;
    }

    @Override
    public boolean delPermission(Integer id) throws Exception {
        if(id==null){
              throw new Exception();
        }
        int i= permissionDao.delPermission(id);
       
        return i>0;
    }

    @Override
    public boolean updatePermission(SystemPermission permission) {
        int i= permissionDao.updatePermission(permission);
        return i>0;
    }

    @Override
    public SystemPermission getPermissionById(int id) {

        return permissionDao.getPermissionById(id);
    }

    @Override
    public List<SystemPermission> getPermissionListByRoleId(int rid) {        
    	List<SystemPermission> s= permissionDao.getPermissionListByRoleId(rid);
        
        return s;
    }

    
}
