package com.cs.system.service;

import com.cs.system.entity.SystemUser;
import org.springframework.stereotype.Service;

/**
 * Created by s0c00q3 on 2017/2/23.
 */

public interface UserService {
    SystemUser login(String username);
    boolean addUser(SystemUser user);
    boolean updateUser(SystemUser user);
    boolean deleteUser(SystemUser user);
}
