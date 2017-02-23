package com.cs.system.service.impl;

import com.cs.system.dao.SystemUserDao;
import com.cs.system.entity.SystemUser;
import com.cs.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by s0c00q3 on 2017/2/23.
 */
@Service
public class SystemUserServiceImpl implements SystemUserService{
    @Autowired
    private SystemUserDao userDao;
    @Override
    public SystemUser login(String username) {
        SystemUser user=userDao.login(username);
        return user;
    }

    @Override
    public boolean addUser(SystemUser user) {
        int i=userDao.addUser(user);
        return i>0;
    }

    @Override
    public boolean updateUser(SystemUser user) {
        int i=userDao.updateUser(user);
        return i>0;
    }

    @Override
    public boolean deleteUser(String username) {
        int i=userDao.deleteUser(username);
        return i>0;
    }
}
