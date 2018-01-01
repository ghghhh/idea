package com.cs.cj;

import java.util.HashMap;
import java.util.Map;

import com.cs.common.utils.HttpClientUtils;

public class Test {

	public static void main(String[] args){
		long start=System.currentTimeMillis();
		Map map=new HashMap();
		map.put("name", "陈胜");
		map.put("name1", "cs1");
        String s=HttpClientUtils.getRequest("http://localhost:8888/test",map);
        System.out.println("Test.main()="+String.valueOf(System.currentTimeMillis()-start));
        System.out.println(s);
	}

}
