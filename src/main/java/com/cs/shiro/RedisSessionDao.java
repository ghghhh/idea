package com.cs.shiro;

import com.cs.common.utils.ServletUtil;
import com.cs.common.utils.UserUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by s0c00q3 on 2017/3/1.
 */
public class RedisSessionDao extends AbstractSessionDAO{

    private static final Logger logger= LoggerFactory.getLogger(RedisSessionDao.class);
    @Autowired
    private ServletUtil servletUtil;

    @Autowired
    private RedisTemplate redisTemplate;
    private static final String CACHE_KEY="session_";
    protected Serializable doCreate(Session session) {

        if(this.isStaticFile()){
            return null;
        }else{
            Serializable sessionId = generateSessionId(session);
            assignSessionId(session, sessionId);
            redisTemplate.opsForValue().set(CACHE_KEY+sessionId,session,session.getTimeout(),TimeUnit.MILLISECONDS);
            return sessionId;
        }
    }

    protected Session doReadSession(Serializable sessionId) {
        if(this.isStaticFile()){
            return null;
        }else{
            logger.info("redis-----------get--------sessionId:{}",sessionId);
            return (Session)redisTemplate.opsForValue().get(CACHE_KEY+sessionId);
        }
    }

    private boolean isStaticFile(){
        HttpServletRequest request=servletUtil.getRequest();
        String url=request.getServletPath();
        if(servletUtil.isStaticFile(url)){
            logger.info("静态文件url：{}不生成session",url);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        if(this.isStaticFile()){
            return ;
        }else{
            if(session.getId()!=null){
                logger.info("redis-----------update--------sessionId:{}",session.getId());
                redisTemplate.opsForValue().set(CACHE_KEY+session.getId(),session,session.getTimeout(),TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public void delete(Session session) {
        if(this.isStaticFile()){
            return ;
        }else{
            if(session.getId()!=null){
                redisTemplate.delete(CACHE_KEY+session.getId());
            }
        }
    }

    public void deleteById(String sessionId) {
        redisTemplate.delete(CACHE_KEY+sessionId);
    }

    @Override
    public Collection<Session> getActiveSessions() {
       return null;
    }
}
