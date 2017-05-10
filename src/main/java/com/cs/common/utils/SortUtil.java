package com.cs.common.utils;

import com.cs.system.entity.SysUserForm;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by s0c00q3 on 2017/5/10.
 */
public class SortUtil {
    public static String change(Object o) throws Exception {
        Class first=o.getClass();
        Field f=first.getDeclaredField("sort");
        if(f==null){
            return null;
        }
        f.setAccessible(true);
        String field=(String)f.get(o);

        try{
            first.getDeclaredField(field);
        }catch (Exception e){
            try{
                first.getSuperclass().getDeclaredField(field);
            }catch (Exception e1){
               return null;
            }

        }
        StringBuilder sb=new StringBuilder(field.length()+5);
        for(int i=0;i<field.length();i++){
            char c=field.charAt(i);
            if(Character.isUpperCase(c)){
                sb.append('_');
                c=Character.toLowerCase(c);
            }
            sb.append(c);
        }

        return sb.toString();
    }
}
