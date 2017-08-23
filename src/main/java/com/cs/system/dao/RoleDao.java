package com.cs.system.dao;
import com.cs.system.entity.SystemRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/16.
 */
@Mapper
public interface RoleDao {
    int addRole(SystemRole systemRole);
    int updateRole(SystemRole systemRole);
    int delRoleById(int id);
    SystemRole getRoleById(int id);
    List<SystemRole> getRoleListByUserId(long uid);   
}
