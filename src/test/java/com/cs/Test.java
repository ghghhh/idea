package com.cs;

import com.cs.system.entity.SystemUser;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.jni.Local;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
public class Test {
    public static void test (){
        float f1=20;
        float f2=20.5f;
        float f3=20.3f;
        double d1=20;
        double d2=20.5;
        double d3=20.3;
        System.out.println(f1==d1);
        System.out.println(f2==d2);
        System.out.println(f3==d3);
    }

    public static char[] byteTochar(byte[] b){
        Charset cs=Charset.forName("utf-8");
        ByteBuffer bb=ByteBuffer.allocate(b.length);
        bb.put(b);
        bb.flip();
        CharBuffer cb=cs.decode(bb);
        char[]c=new char[cb.limit()];
        cb.get(c);

        return c;
    }

    public static void main(String[] args){
        String password="cs";
        System.out.println(DigestUtils.sha512Hex(password));
    }
}
