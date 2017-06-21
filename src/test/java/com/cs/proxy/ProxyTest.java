package com.cs.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by s0c00q3 on 2017/3/23.
 */
public class ProxyTest {
    public static void main(String[] args) {
        Book book=new BookImpl();
        BookProxy proxy=new BookProxy(book);
        Book b=(Book)Proxy.newProxyInstance(book.getClass().getClassLoader(),book.getClass().getInterfaces(),proxy);
        b.addBook("ccc");
    }
}
