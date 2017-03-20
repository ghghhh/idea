package com.cs.system.service.impl;

import com.cs.system.dao.SystemRoleDao;
import com.cs.system.entity.SystemRole;
import com.cs.system.service.SystemRoleServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
@Service
public class SystemRoleServiveImpl implements SystemRoleServive {
    @Autowired
    private SystemRoleDao systemRoleDao;
    @Override
    public SystemRole getRoleById(int id) {
        return systemRoleDao.getRoleById(id);
    }

    @Override
    public boolean createRole(SystemRole role) {
        return systemRoleDao.addRole(role) > 0 ? true:false;
    }

    @Override
    public boolean delRole(int id) {
        return systemRoleDao.delRoleById(id) > 0 ?true:false;
    }

    @Override
    public boolean updateRole(SystemRole role) {
        return false;
    }

    @Override
    public List<SystemRole> getRoleListByUserId(long uid) {
        List<SystemRole> list= systemRoleDao.getRoleListByUserId(uid);
        return list;
    }

}
