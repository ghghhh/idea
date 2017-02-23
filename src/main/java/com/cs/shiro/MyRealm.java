package com.cs.shiro;

import com.cs.system.entity.SystemUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by s0c00q3 on 2017/2/22.
 */
public class MyRealm extends AuthorizingRealm{

    private final Logger log= LoggerFactory.getLogger(MyRealm.class);
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken= (UsernamePasswordToken)token;
        String name=userToken.getUsername();
        char[] password=userToken.getPassword();
        String pass=new String(password);
        SystemUser m=new SystemUser();
        m.setUserName(name);
        m=myusersService.login(name);
        if(m!=null&&m.getUserPassword().equals(DigestUtils.sha512Hex(pass))){
            AuthenticationInfo info=new SimpleAuthenticationInfo(m.getUserName(), password, m.getUserName());
            log.info("用户{}登录成功",name);
            return info;
        }
        log.info("用户{}登录失败",name);
        return null;
    }
}
