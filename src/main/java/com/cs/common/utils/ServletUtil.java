package com.cs.common.utils;

import com.cs.common.aop.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by s0c00q3 on 2017/3/1.
 */
@Component
public class ServletUtil {

    @Value("${web.staticFile}")
    private String[] staticFile;

    private Map<String,Object> map=new HashMap<>();

    public boolean isStaticFile(String url){
         String[] ss=url.split("\\.");
        //.html
        if(ss!=null&&ss.length>0){
            String urlf=ss[ss.length-1];
            for(String s:staticFile){
                if(urlf.equals(s)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前请求对象
     * @return
     */
    public HttpServletRequest getRequest(){
        try{
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch(Exception e){
            return null;
        }
    }

    public boolean isStaticFile(){
        HttpServletRequest request=this.getRequest();
        String url=request.getServletPath();
        if(this.isStaticFile(url)){
            return true;
        }else{
            return false;
        }
    }
}
