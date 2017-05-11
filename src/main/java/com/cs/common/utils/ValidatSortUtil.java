package com.cs.common.utils;

import com.cs.system.entity.SysUserForm;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by s0c00q3 on 2017/5/10.
 */
public class ValidatSortUtil {
    /**
     * 验证前端传入的 sort order 等排序参数是否合法，并将参数转为对应的数据库列名
     *
     * */
    public static void validat(Object o){
        Class first=o.getClass();
        try {
            Field sort = first.getDeclaredField("sort");
            sort.setAccessible(true);
            String sortValue = (String)sort.get(o);
            while(first!= Object.class){
                try{
                    first.getDeclaredField(sortValue);
                }catch (Exception e){
                    first=first.getSuperclass();
                    continue;
                }
                StringBuilder sb=new StringBuilder(sortValue.length()+5);
                for(int i=0;i<sortValue.length();i++){
                    char c=sortValue.charAt(i);
                    if(Character.isUpperCase(c)){
                        sb.append('_');
                        c=Character.toLowerCase(c);
                    }
                    sb.append(c);
                }
                sort.set(o,sb.toString());

                try {
                    Field order=o.getClass().getDeclaredField("order");
                    order.setAccessible(true);
                    String orderValue=(String)order.get(o);
                    if(!"asc".equals(orderValue.toLowerCase())&&!"desc".equals(orderValue.toLowerCase())){
                        order.set(o,"asc");
                    }
                } catch (Exception e) {
                }
                return;
            }
            sort.set(o,null);
        } catch (Exception e) {
        }
        return;
    }
}
