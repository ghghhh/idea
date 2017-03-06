package com.cs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScmApplicationTests {

	@Autowired
	private StringRedisTemplate con;

	private static final Logger logger=LoggerFactory.getLogger(ScmApplicationTests.class);
	@Test
	public void contextLoads() {
		logger.info("info");
		logger.debug("debug");
		con.opsForValue().set("name","cs");
		String s=con.boundValueOps("name").get();
		String s1=con.opsForValue().get("name");
		System.out.print(s+"------------");
	}

}
