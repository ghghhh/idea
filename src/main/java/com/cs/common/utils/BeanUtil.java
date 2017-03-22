package com.cs.common.utils;
import static com.cs.ScmApplication.context;
/**
 * Created by s0c00q3 on 2017/3/16.
 */
public class BeanUtil {

    public static <T>T getBean(Class<T> classType){
        return context.getBean(classType);
    }
}
