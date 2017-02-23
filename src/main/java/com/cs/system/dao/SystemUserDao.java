package com.cs.system.dao;

import com.cs.system.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by s0c00q3 on 2017/2/23.
 */
@Mapper
public interface SystemUserDao {
    SystemUser login(String username);
    int addUser(SystemUser user);
    int updateUser(SystemUser user);
    int deleteUser(String username);
}
