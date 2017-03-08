package com.cs.common.utils;

import com.cs.common.baseEntity.BaseRequestDTO;
import org.apache.shiro.SecurityUtils;

import java.time.LocalDateTime;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
public class UserUtils {
    public static void updateByUser( BaseRequestDTO dto){
        String username= (String)SecurityUtils.getSubject().getPrincipal();
        dto.setUpdateBy(username);
        dto.setUpdateDate(LocalDateTime.now());
    }
    public static void createByUser( BaseRequestDTO dto){
        String username= (String)SecurityUtils.getSubject().getPrincipal();
        dto.setCreateBy(username);
        dto.setCreateDate(LocalDateTime.now());
    }
}
