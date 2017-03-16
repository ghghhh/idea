package com.cs.shiro;

import com.cs.common.utils.BeanUtil;
import com.cs.common.utils.ServletUtil;
import org.apache.shiro.subject.Subject;
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
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        if(util.isStaticFile()){
            return true;
        }else{
            return super.isAccessAllowed(request,response,buildPerm(request,response));
        }
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
