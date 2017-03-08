package com.cs.config;


import com.cs.shiro.FormFiler;
import com.cs.shiro.MyRealm;
import com.cs.shiro.Myfilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.config.AbstractShiroConfiguration;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by s0c00q3 on 2017/2/22.
 * spring boot 的bean初始化 约定的是自定义的优先于starter里的默认的bean初始化
 */
@Configuration
public class ShiroConfig {

    @Value("${server.contextPath}")
    private String path;
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Bean(name = "filterShiroFilterRegistrationBean")
    protected FilterRegistrationBean filterShiroFilterRegistrationBean() throws Exception {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        Map<String,Filter> map=new LinkedHashMap<>();
        FormFiler filter=new FormFiler();
        filter.setContextPath(path);
        map.put("authc",filter);
        map.put("only",new Myfilter());
        shiroFilterFactoryBean.setFilters(map);
        filterRegistrationBean.setFilter((AbstractShiroFilter) shiroFilterFactoryBean.getObject());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }


}
