package com.cs;

import com.cs.system.entity.SystemPermission;
import com.cs.system.service.PermissionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by s0c00q3 on 2017/3/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermsTest {
    @Autowired
    private PermissionService systemPermissionService;
    @org.junit.Test
    public void update(){
        SystemPermission p=new SystemPermission();
        p.setId(1);
        p.setPermissionUrl("/do");
        systemPermissionService.updatePermission(p);
    }
}
