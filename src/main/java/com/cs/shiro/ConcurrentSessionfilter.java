package com.cs.shiro;

import com.cs.system.entity.SystemUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/8.
 */
public class ConcurrentSessionfilter extends AccessControlFilter{
    private static final String LOGINED="logined_";

    private int loginNum=1;
    private RedisTemplate redisTemplate;
    private RedisSessionDao redisSessionDao;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject=getSubject(request,response);
        return false;
    }

    @Override
    //返回true 继续处理后续filter
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        long l=0;
        Subject subject=getSubject(request,response);
        if(!subject.isAuthenticated()){
            return true;
        }else{
            SystemUser user=(SystemUser)subject.getPrincipal();
            String cacheKey=LOGINED+user.getUserName();
            String sid=(String)subject.getSession().getId();
            List<String> list=redisTemplate.opsForList().range(cacheKey,0,loginNum);
            if(list==null){
                l=redisTemplate.opsForList().leftPush(cacheKey,sid);
                if(l>loginNum){
                    String sessionId =(String)redisTemplate.opsForList().rightPop(cacheKey);
                    //删除该session
                    redisSessionDao.deleteById(sessionId);
                }
                return true;
            }
            if(list.contains(sid)){
                if(list.size()<=loginNum){
                    return true;
                }else{
                    String sessionId=(String)redisTemplate.opsForList().rightPop(cacheKey);
                    //删除该session
                    redisSessionDao.deleteById(sessionId);
                    if(sid.equals(sessionId)){
                        this.redirectToLogin(request,response);
                        return false;
                    }
                }

            }else{
                l=redisTemplate.opsForList().leftPush(cacheKey,sid);
                if(l>loginNum){
                    String sessionId =(String)redisTemplate.opsForList().rightPop(cacheKey);
                    //删除该session
                    redisSessionDao.deleteById(sessionId);
                    if(sid.equals(sessionId)){
                        this.redirectToLogin(request,response);
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setRedisSessionDao(RedisSessionDao redisSessionDao) {
        this.redisSessionDao = redisSessionDao;
    }
}
