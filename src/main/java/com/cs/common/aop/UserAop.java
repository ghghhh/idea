package com.cs.common.aop;

import com.cs.common.baseEntity.BaseRequestDTO;
import com.cs.common.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
//@Component
//@Aspect
public class UserAop {
    private Logger log = LoggerFactory.getLogger(UserAop.class);

    @Pointcut("execution(* com.cs..*(..))")
    public void service(){}
    @Before("service()")
    public void doSome(JoinPoint jp){
        Object[] args=jp.getArgs();
        MethodSignature s=(MethodSignature)jp.getSignature();
        Annotation[][] annotations=s.getMethod().getParameterAnnotations();
        log.info(jp.getTarget().toString());
        for(int i=0;i<annotations.length;i++){
            for(Annotation a:annotations[i]){
                if(a instanceof Update){
                    if(args[i] instanceof BaseRequestDTO){
                        BaseRequestDTO dto=(BaseRequestDTO)args[i];
                        UserUtils.updateByUser(dto);
                    }
                }
            }
        }
    }

    public void check(Annotation annotation,Object args){
        if(annotation instanceof Update){
            BaseRequestDTO dto=(BaseRequestDTO)args;
            UserUtils.updateByUser(dto);
        }
    }
}
