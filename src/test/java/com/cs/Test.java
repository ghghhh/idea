package com.cs;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * Created by s0c00q3 on 2017/3/20.
 */
public class Test {
    public static void test (){
        int i=1;
        i=i+1;
        System.out.println(i);
    }

    public static int sheng(int i){
    	if(i==1){
    		return 1;
    	}else{
    		return i*sheng(i-1);
    	}
    	
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
    	double i=123.3;
    	System.out.println((byte)i);
    	ExecutorService pool=Executors.newFixedThreadPool(5,r->{
    		Thread t=new Thread(r);
    		t.setUncaughtExceptionHandler((t1,e)->{
    			System.out.println(t1+" --ex-- "+e);
    		});
    		return t;
    	});
    	pool.submit(()->{
    		Object o=null;    		
    			System.out.println(o.toString());    		
    	});
    	pool.execute(()->{
    		Object o=null;   		   		
    			System.out.println(o.toString());
    	});
    	pool.shutdown();
    }
}
