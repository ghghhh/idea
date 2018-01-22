package com.cs.system.dao;

import com.cs.system.entity.SysUserForm;
import com.cs.system.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by s0c00q3 on 2017/2/23.
 */
@Mapper
public interface UserDao {
    SystemUser login(String username);
    int addUser(SystemUser user);
    int updateUser(SystemUser user);
    int deleteUser(SystemUser user);
    List<SystemUser> getUserList(SysUserForm user);
}
