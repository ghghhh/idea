package com.cs.shiro;

import com.cs.common.utils.ServletUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by s0c00q3 on 2017/3/1.
 */
public class RedisSessionDao extends AbstractSessionDAO{

    private static final Logger logger= LogManager.getLogger(RedisSessionDao.class);
    @Autowired
    private ServletUtil servletUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private static final String CACHE_KEY="session_";
    private ConcurrentMap<Serializable, Session> sessions;

    public RedisSessionDao() {
        this.sessions = new ConcurrentHashMap<Serializable, Session>();
    }
    protected Serializable doCreate(Session session) {
            Serializable sessionId = generateSessionId(session);
            assignSessionId(session, sessionId);
            sessions.putIfAbsent(sessionId, session);
            redisTemplate.opsForValue().set(CACHE_KEY+sessionId,session,session.getTimeout(),TimeUnit.MILLISECONDS);
            return sessionId;
    }

    protected Session doReadSession(Serializable sessionId) {
            logger.debug("redis-----------get--------sessionId:{}",sessionId);
            Object o=redisTemplate.opsForValue().get(CACHE_KEY+sessionId);
            return (Session)o;
    }

    private boolean isStaticFile(){
        HttpServletRequest request=servletUtil.getRequest();
        String url=request.getServletPath();
        if(servletUtil.isStaticFile(url)){
            logger.debug("url:{}为静态文件",url);
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
                logger.debug("redis-----------update--------sessionId:{}",session.getId());
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
                logger.debug("redis-----------del--------sessionId:{}",session.getId());
                redisTemplate.delete(CACHE_KEY+session.getId());
            }
        }
    }

    public void deleteById(String sessionId) {
        logger.debug("redis-----------delById--------sessionId:{}",sessionId);
        redisTemplate.delete(CACHE_KEY+sessionId);
    }

    @Override
    public Collection<Session> getActiveSessions() {
       return null;
    }
}
