package com.cs.config;


import com.cs.common.utils.ServletUtil;
import com.cs.shiro.FormFiler;
import com.cs.shiro.MyPermsFilter;
import com.cs.shiro.RedisSessionDao;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by s0c00q3 on 2017/2/22.
 * spring boot 的bean初始化 约定的是自定义的优先于starter里的默认的bean初始化
 */
@Configuration
public class ShiroConfig {

    @Value("${server.contextPath}")
    private String path;

    @Value("${shiro.login.maxLoginNum}")
    private int loginNum;
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisSessionDao redisSessionDao;
    @Autowired
    private ServletUtil servletUtil;
    @Bean(name = "filterShiroFilterRegistrationBean")
    protected FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Map<String,Filter> map=new LinkedHashMap<>();
        map.put("authc",formFiler());
        map.put("perms",myPermsFilter());
        shiroFilterFactoryBean.setFilters(map);
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    public FormFiler formFiler(){
        FormFiler filter=new FormFiler();
        filter.setLoginNum(loginNum);
        filter.setRedis(redisTemplate);
        filter.setRedisSessionDao(redisSessionDao);
        filter.setContextPath(path);
        return filter;
    }
    public MyPermsFilter myPermsFilter(){
        MyPermsFilter perm=new MyPermsFilter();
        perm.setUtil(servletUtil);
        return perm;
    }
}
