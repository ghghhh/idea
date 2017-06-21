package com.cs.concurrent;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by s0c00q3 on 2017/3/30.
 */
public class NewThread implements Runnable{
    public Map map;
    private int sum;
    private Lock lock=new ReentrantLock(true);
    @Override
    public void run() {
        for(int i=0;i<1000;i++){

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println("线程"+Thread.currentThread().getName()+"获得锁");
            sum++;
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }
}
