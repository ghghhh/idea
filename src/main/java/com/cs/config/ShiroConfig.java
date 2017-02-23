package com.cs.config;


import com.cs.shiro.MyRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by s0c00q3 on 2017/2/22.
 */
@Configuration
public class ShiroConfig {


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
}
