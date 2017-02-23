package com.cs.config;


import com.cs.ScmApplication;
import com.cs.shiro.FormFiler;
import com.cs.shiro.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * Created by s0c00q3 on 2017/2/22.
 */
@Configuration
public class ShiroConfig{
    
    @Bean
    public Realm realm(){
        MyRealm r=new MyRealm();
        return r;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login.html", "anon"); // need to accept POSTs from the login form
        chainDefinition.addPathDefinition("/js/*","anon");
        chainDefinition.addPathDefinition("/html/*","anon");
        chainDefinition.addPathDefinition("/css/*","anon");
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/**","authc");
        return chainDefinition;
    }

    @Bean
    @ConditionalOnBean
    public FormFiler formFiler() throws Exception{
        FormFiler filer=new FormFiler();
        FilterRegistrationBean bean=(FilterRegistrationBean)ScmApplication.context.getBean("filterShiroFilterRegistrationBean");
        AbstractShiroFilter shiroFilter=(AbstractShiroFilter)bean.getFilter();
        PathMatchingFilterChainResolver chainResolver =(PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();
        chainResolver.getFilterChainManager().addFilter("asas",filer);
        return filer;
    }
}
