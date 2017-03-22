package com.cs.system.service.impl;

import static com.cs.common.utils.CacheName.PERMISSIONS_ROLE;
import com.cs.system.dao.PermissionDao;
import com.cs.system.entity.SystemPermission;
import com.cs.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean addPermission(SystemPermission permission) {
       int i= permissionDao.addPermission(permission);
        return i>0;
    }

    @Override
    public boolean delPermission(Integer id) throws Exception {
        if(id==null){
              throw new Exception();
        }
        int i= permissionDao.delPermission(id);
        if(i>0){
            delRoleCacheByPerms(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePermission(SystemPermission permission) {
        int i= permissionDao.updatePermission(permission);
        if(i>0){
            delRoleCacheByPerms(permission.getId());
            return true;
        }
        return false;
    }

    @Override
    public SystemPermission getPermissionById(int id) {

        return permissionDao.getPermissionById(id);
    }

    @Override
    public List<SystemPermission> getPermissionListByRoleId(int rid) {
        List<SystemPermission> s=redisTemplate.opsForList().range(PERMISSIONS_ROLE+rid,0,-1);
        if(s!=null&&s.size()>0){
            return s;
        }else{
            s= permissionDao.getPermissionListByRoleId(rid);
            redisTemplate.opsForList().leftPushAll(PERMISSIONS_ROLE+rid,s);
        }
        return s;
    }

    private void delRoleCacheByPerms(int id){
        List<Integer> list= permissionDao.getRoleIdListByPerms(id);
        if(list!=null&&!list.isEmpty()){
            list.forEach(l->redisTemplate.delete(PERMISSIONS_ROLE+l));
        }
    }
}