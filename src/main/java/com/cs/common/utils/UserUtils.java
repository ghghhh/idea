package com.cs.common.utils;

import com.cs.common.baseEntity.BaseRequestDTO;
import com.cs.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;

import java.time.LocalDateTime;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
public class UserUtils {
    public static void updateByUser( BaseRequestDTO dto){
        SystemUser user= (SystemUser)SecurityUtils.getSubject().getPrincipal();
        dto.setUpdateBy(user.getUserName());
        dto.setUpdateDate(LocalDateTime.now());
    }
    public static void createByUser( BaseRequestDTO dto){
        SystemUser user= (SystemUser)SecurityUtils.getSubject().getPrincipal();
        dto.setCreateBy(user.getUserName());
        dto.setCreateDate(LocalDateTime.now());
    }
    public static String getUserName(){
        SystemUser user= (SystemUser)SecurityUtils.getSubject().getPrincipal();
        if(user==null){
            return null;
        }
        return user.getUserName();
    }
}
