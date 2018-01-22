package com.cs.system.controller;

import com.cs.common.utils.ValidatFormUtil;
import com.cs.system.entity.SysUserForm;
import com.cs.system.entity.SystemUser;
import com.cs.system.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s0c00q3 on 2017/5/4.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/user/getList")
    public Object getUserList(SysUserForm userForm, int page, int rows) throws Exception {
        Map<String,Object> map=new HashMap<>();
        if(userForm.getSort()!=null){
            ValidatFormUtil.validatSort(userForm);
        }
        List<SystemUser> list=userService.getUserList(userForm);

        Page<SystemUser> pageUser=(Page<SystemUser>)list;
        map.put("rows",pageUser);
        map.put("total",pageUser.getTotal());
        return map;
    }
}
