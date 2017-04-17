package com.cs.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by s0c00q3 on 2017/3/30.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Map<String,Object> map=new ConcurrentHashMap<>();
        NewThread t=new NewThread();
        Thread t1=new Thread(t);
        Thread t2=new Thread(t);
        Thread t3=new Thread(t);
        t1.start();
        t2.start();
        t3.start();
        while (true){
            Thread.sleep(100);
            System.out.println(t.getSum());
        }
    }
}
