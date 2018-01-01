package com.cs;

import com.cs.system.entity.SystemRole;
import com.cs.system.entity.SystemUser;
import com.cs.system.service.RoleServive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by s0c00q3 on 2017/3/16.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RoleTest {
    private Logger log = LoggerFactory.getLogger(RoleTest.class);
    //@Autowired
    private RoleServive roleServive;

    //@Test
    public void Test(){
        long l1=System.currentTimeMillis();

        SystemRole r=roleServive.getRoleById(1);

        log.info("time"+(System.currentTimeMillis()-l1));
        //System.out.print(r.getCnName());
    }

    public static void main(String[] args) {
    	 int i=1;
         i=i+1;
         System.out.println(i);
	}

}
