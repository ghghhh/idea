package com.cs.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by s0c00q3 on 2017/3/23.
 */

public class BookProxy implements InvocationHandler{
    private Object target;

    public BookProxy(Object target) {
        this.target = target;
    }
    
    @Override   
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("add book proxy begin");
        method.invoke(target,args);
        System.out.println("add book proxy end");
        return null;
    }
}
