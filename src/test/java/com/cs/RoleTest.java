package com.cs;

import com.cs.system.service.RoleServive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by s0c00q3 on 2017/3/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleTest {
    @Autowired
    private RoleServive roleServive;

    @Test
    public void Test(){
        roleServive.getRoleById(1);
    }


}
