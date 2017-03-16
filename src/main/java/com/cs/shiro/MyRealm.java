package com.cs.shiro;

import com.cs.system.entity.SystemUser;
import com.cs.system.service.SystemUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by s0c00q3 on 2017/2/22.
 */
public class MyRealm extends AuthorizingRealm{

    private final Logger log= LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private SystemUserService systemUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRole("admin");
        info.addStringPermission("admin");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken= (UsernamePasswordToken)token;
        String name=userToken.getUsername();
        char[] password=userToken.getPassword();
        String pass=new String(password);
        try{
            SystemUser m=systemUserService.login(name);
            if(m!=null&&m.getUserPassword().equals(DigestUtils.sha512Hex(pass))){
                AuthenticationInfo info=new SimpleAuthenticationInfo(m, password, getName());
                log.debug("用户{}登录成功",name);
                return info;
            }
            log.debug("用户{}登录失败",name);
        }catch (Exception e){
            log.error("用户{}登录失败",name);
        }
        return null;
    }
}
