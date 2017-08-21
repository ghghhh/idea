package com.cs.config;

import com.cs.shiro.MyRealm;
import com.cs.shiro.RedisSessionDao;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

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
        chainDefinition.addPathDefinition("/login", "authc");
        chainDefinition.addPathDefinition("/js/**", "anon");
        chainDefinition.addPathDefinition("/css/**", "anon");
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/**", "user,consession,perms");
        //chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

    @Bean
    public RedisSessionDao sessionDAO(){
        RedisSessionDao dao=new RedisSessionDao();
        return dao;
    }

    @Bean(name="redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate=new RedisTemplate<>();
        StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    //@Bean(name="multipartResolver")
    public StandardServletMultipartResolver multipartResolver(){
        StandardServletMultipartResolver multipartResolver=new StandardServletMultipartResolver();
        return multipartResolver;
    }
}
