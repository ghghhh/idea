package com.cs.system.service.impl;

import com.cs.system.dao.RoleDao;
import com.cs.system.dao.UserDao;
import com.cs.system.entity.SystemRole;
import com.cs.system.service.RoleServive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import static com.cs.common.utils.CacheName.PERMISSIONS_ROLE;
import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
@Service
public class RoleServiveImpl implements RoleServive {
    private Logger log = LoggerFactory.getLogger(RoleServiveImpl.class);

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public SystemRole getRoleById(int id) {
         userDao.login("cs");
         log.info("--------");
         return roleDao.getRoleById(id);

    }

    @Override
    public boolean createRole(SystemRole role) {
        return roleDao.addRole(role)>0?true:false;
    }

    @Override
    public boolean delRole(int id) {
        int i=roleDao.delRoleById(id);
        if(i>0){
            delRoleCacheByRoleId(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateRole(SystemRole role) {
        int i=roleDao.updateRole(role);
        if(i>0){
            return true;
        }
        return false;
    }

    @Override
    public List<SystemRole> getRoleListByUserId(long uid) {
        List<SystemRole> list= roleDao.getRoleListByUserId(uid);
        return list;
    }

    private void delRoleCacheByRoleId(int id){
        redisTemplate.delete(PERMISSIONS_ROLE+id);
    }
}
