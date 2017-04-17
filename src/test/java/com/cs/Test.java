package com.cs;



import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
public class Test {
    public static void main (String[] args) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
       /* Class c=Test.class;
        Method[] mm= c.getDeclaredMethods();
        for(Method m:mm){
            if(m.getName().equals("byteTochar")){
               Class[] p= m.getParameterTypes();
                System.out.println(p[0].getName());
                Method me=c.getDeclaredMethod("byteTochar",byte[].class);
                char[] o= (char[]) m.invoke(c,"ggg".getBytes());
                System.out.println(new String(o));
            }
        }*/
       char[] c= byteTochar("大颯颯大".getBytes());
        System.out.println(new String(c));
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

    public static byte[] random(){
        SecureRandom random = new SecureRandom();
        byte[] b=new byte[64];
        random.nextBytes(b);
        String s=Hex.encodeHexString(b);
        System.out.println(s);
        System.out.println(s.length());
        return b;
    }
}
