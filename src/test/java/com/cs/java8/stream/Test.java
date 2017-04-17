package com.cs.java8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by s0c00q3 on 2017/4/17.
 */
public class Test {
    public static void main(String[] args) {
        Stream<String> s = Stream.of("I","Love","You","Too");
        List<String> list=s.filter(e->e.toLowerCase().length()>=3).collect(Collectors.toList());
        list.forEach(e-> System.out.println(e));
    }
}
