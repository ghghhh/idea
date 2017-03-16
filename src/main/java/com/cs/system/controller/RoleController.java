package com.cs.system.controller;

import com.cs.system.entity.SystemRole;
import com.cs.system.service.SystemRoleServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by s0c00q3 on 2017/3/16.
 */
@RestController("role")
public class RoleController {
    @Autowired
    private SystemRoleServive systemRoleServive;

    @RequestMapping(value="get")
    public SystemRole getRole(int id){
        SystemRole role= systemRoleServive.getRoleById(id);
        return role;
    }
}
