package com.cs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScmApplicationTests {

	@Autowired
	private StringRedisTemplate con;
	@Test
	public void contextLoads() {
		con.delete("name");
		String s=con.boundValueOps("name").get();
		System.out.print(s+"------------");
	}

}
