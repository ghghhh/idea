package com.cs.java8;

/**
 * Created by s0c00q3 on 2017/4/10.
 */
public interface InterfaceA {
    default void say(){
        System.out.println("I am A");
    }
}
