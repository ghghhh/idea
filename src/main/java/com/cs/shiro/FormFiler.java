package com.cs.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

public class FormFiler extends FormAuthenticationFilter {

	private String contextPath;
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
		return true;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}
