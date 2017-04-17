package com.cs.proxy;

/**
 * Created by s0c00q3 on 2017/3/23.
 */
public class BookImpl implements Book{
    @Override
    public void addBook(String name) {
        System.out.println("add book "+name+"success");
    }
}
