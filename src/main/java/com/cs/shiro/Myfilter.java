package com.cs.shiro;

import com.cs.system.entity.SystemUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by s0c00q3 on 2017/3/8.
 */
public class Myfilter extends AccessControlFilter{
    private static final String LOGINED="logined_";

    @Value("#{ @environment['shiro.loginNum'] ?: 1 }")
    private int loginNum;
    @Autowired
    private RedisTemplate<String,Session> redisTemplate;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject=getSubject(request,response);
        return subject.isAuthenticated();
    }

    @Override
    //返回true 继续处理后续filter
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject=getSubject(request,response);
        if(!subject.isAuthenticated()){
            return true;
        }else{
            SystemUser user=(SystemUser)subject.getPrincipal();
            String cacheKey=LOGINED+user.getUserName();
            long l=redisTemplate.opsForList().leftPush(cacheKey,subject.getSession());
            if(l>loginNum){
                Session session = redisTemplate.opsForList().rightPop(cacheKey);
                redisTemplate.opsForZSet();
            }
            return true;
        }
    }
}
