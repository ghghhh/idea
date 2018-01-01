package com.cs.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.data.redis.core.RedisTemplate;

import com.cs.system.entity.SystemUser;

public class FormFiler extends FormAuthenticationFilter {

	private static final String LOGINED="logined_";
	private String contextPath;
	private int loginNum;
	private RedisSessionDao redisSessionDao;
	private RedisTemplate<String, Object> redis;
	public final static String URL="request_url";
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
        Session session = subject.getSession();
        if(savedRequest!=null){
        	String requestUrl= savedRequest.getRequestUrl();
        	if(requestUrl.startsWith(contextPath)){
        		session.setAttribute(URL, requestUrl);
            }      	
        }
        //保存登录成功后的session至缓存 方便实现同时登录人数限制
        SystemUser user=(SystemUser)subject.getPrincipal();;
        long num=redis.opsForList().leftPush(LOGINED+user.getUserName(), session.getId());
        if(num>loginNum){
        	String s=(String)redis.opsForList().rightPop(LOGINED+user.getUserName());
        	redisSessionDao.deleteById(s);
        }
		return true;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public void setRedis(RedisTemplate<String, Object> redis) {
		this.redis = redis;
	}

	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}

	public void setRedisSessionDao(RedisSessionDao redisSessionDao) {
		this.redisSessionDao = redisSessionDao;
	}
	
}
