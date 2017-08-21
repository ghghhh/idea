package com.cs.system.controller;
import com.cs.common.baseEntity.ReturnObject;
import com.cs.system.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

import static com.cs.shiro.FormFiler.URL;
@Controller
public class LoginController {
	
	
	final Logger log=LoggerFactory.getLogger(LoginController.class);
    
	private final static String successUrl="/cs/html/main.html";
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login0(SystemUser myusers,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		Object o=subject.getPrincipal();
		if(o==null){
			return "index";
		}else{
			return "redirect:/main";
		}
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Object login(HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		ReturnObject r=new ReturnObject();

	    if(subject.getPrincipal()!=null){
			Session session=subject.getSession();
			Object url=session.getAttribute(URL);
			r.setCode("200");
			if(url!=null){
				r.setData(url);
			}else{
                r.setData(successUrl);
			}
			r.setMsg("login success");
		}else{
			r.setCode("500");
			r.setMsg("login fail");
		}
		return r;
	}
	@RequestMapping(value = "/logout")
	@ResponseBody
	public Object logout(){
		Subject subject=SecurityUtils.getSubject();
		subject.logout();
		ReturnObject r=new ReturnObject();
		return r;
	}
}
