package com.cs.shiro;

import com.cs.system.entity.SystemUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.data.redis.core.RedisTemplate;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by s0c00q3 on 2017/3/8.
 */
public class ConcurrentSessionfilter extends AccessControlFilter{
    private static final String LOGINED="logined_";

    private int loginNum;
    private RedisTemplate redisTemplate;
    private RedisSessionDao redisSessionDao;
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //Subject subject=getSubject(request,response);
        return false;
    }

    @Override
    //返回true 继续处理后续filter
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        long l=0;
        Subject subject=getSubject(request,response);
        SystemUser user=(SystemUser)subject.getPrincipal();
        String cacheName=LOGINED+user.getUserName();
        List<String> list=redisTemplate.opsForList().range(cacheName,0,loginNum);
        String sid=(String)subject.getSession().getId();
        if(list==null){
            l=redisTemplate.opsForList().leftPush(cacheName,sid);
            checkNum(l,cacheName);
        }else{
            if(list.contains(sid)){
                return true;
            }else{
                /*if(subject.isRemembered()&&list.size()>=loginNum){
                    subject.logout();
                    this.saveRequestAndRedirectToLogin(request ,response );
                    return false;
                }*/
                l=redisTemplate.opsForList().leftPush(cacheName,sid);
                checkNum(l,cacheName);
            }
        }
        return true;
    }


    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setRedisSessionDao(RedisSessionDao redisSessionDao) {
        this.redisSessionDao = redisSessionDao;
    }

    public void setLoginNum(int loginNum) {
        this.loginNum = loginNum;
    }

    public void checkNum(long l,String cacheName){
        if(l>loginNum){
            String rsid=(String)redisTemplate.opsForList().rightPop(cacheName);
            redisSessionDao.deleteById(rsid);
        }
    }
}
