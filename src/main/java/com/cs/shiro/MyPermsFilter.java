package com.cs.shiro;

import com.cs.common.baseEntity.ReturnObject;
import com.cs.common.utils.ServletUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by s0c00q3 on 2017/3/16.
 */
public class MyPermsFilter extends PermissionsAuthorizationFilter{
    private ServletUtil util;
    private final static ObjectMapper mapper=new ObjectMapper();
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if(util.isStaticFile()){
            return true;
        }else{
            return super.isAccessAllowed(request,response,buildPerm(request,response));
        }
    }

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        String url=httpServletRequest.getServletPath();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        ReturnObject r=new ReturnObject();
        r.setCode("401");
        r.setMsg("无权限访问 "+url);
        response.getWriter().write(mapper.writeValueAsString(r));
        return false;
    }
    private String[] buildPerm(ServletRequest request, ServletResponse response){
        String[] perms=new String[1];
        HttpServletRequest r=(HttpServletRequest)request;
        String url=r.getServletPath();
        perms[0]=url;
        return perms;
    }

    public void setUtil(ServletUtil util) {
        this.util = util;
    }
}
