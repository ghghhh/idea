package com.cs.system.dao;

import com.cs.common.aop.Update;
import com.cs.system.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by s0c00q3 on 2017/2/23.
 */
@Mapper
public interface SystemUserDao {
    SystemUser login(String username);
    int addUser(SystemUser user);
    int updateUser(SystemUser user);
    int deleteUser(SystemUser user);
}
