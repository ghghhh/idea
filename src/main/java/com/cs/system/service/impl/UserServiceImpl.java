package com.cs.system.service.impl;

import com.cs.common.aop.Update;
import com.cs.common.utils.UserUtils;
import com.cs.system.dao.UserDao;
import com.cs.system.entity.SysUserForm;
import com.cs.system.entity.SystemUser;
import com.cs.system.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/2/23.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public SystemUser login(@Update String username) {
        SystemUser user=userDao.login(username);
        return user;
    }

    @Override
    public boolean addUser(@Update SystemUser user) {
        int i=userDao.addUser(user);
        return i>0;
    }

    @Override
    public boolean updateUser(SystemUser user) {
        UserUtils.updateByUser(user);
        int i=userDao.updateUser(user);
        return i>0;
    }

    @Override
    public boolean deleteUser(SystemUser user) {
        UserUtils.updateByUser(user);
        int i=userDao.deleteUser(user);
        return i>0;
    }

    @Override
    public List<SystemUser> getUserList(SysUserForm user) {
        PageHelper.startPage(user.getPage(),user.getRows());
        return userDao.getUserList(user);
    }
}
