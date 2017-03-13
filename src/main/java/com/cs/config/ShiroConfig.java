package com.cs.config;


import com.cs.shiro.ConcurrentSessionfilter;
import com.cs.shiro.FormFiler;
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
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisSessionDao redisSessionDao;
    @Bean(name = "filterShiroFilterRegistrationBean")
    protected FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Map<String,Filter> map=new LinkedHashMap<>();
        FormFiler filter=new FormFiler();
        filter.setContextPath(path);
        map.put("authc",filter);
        ConcurrentSessionfilter myfilter=new ConcurrentSessionfilter();
        myfilter.setRedisTemplate(redisTemplate);
        myfilter.setRedisSessionDao(redisSessionDao);
        myfilter.setLoginNum(loginNum);
        map.put("consession",myfilter);
        shiroFilterFactoryBean.setFilters(map);
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


}
