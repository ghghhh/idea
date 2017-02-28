package com.cs.config;

import com.cs.shiro.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by s0c00q3 on 2017/2/28.
 */
@Configuration
public class ShiroBean {

    @Bean
    public Realm realm(){
        MyRealm r=new MyRealm();
        return r;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        //chainDefinition.addPathDefinition("/html/index.html","authc");
        chainDefinition.addPathDefinition("/html/*", "anon");
        chainDefinition.addPathDefinition("/js/*", "anon");
        chainDefinition.addPathDefinition("/css/*", "anon");
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }
}
