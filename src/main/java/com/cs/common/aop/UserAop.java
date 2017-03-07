package com.cs.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by s0c00q3 on 2017/3/7.
 */
@Component
@Aspect
public class UserAop {
    @Pointcut("execution(* com.cs..*.*(..))")
    public void service(){}
    @Before("service()")
    public void doSome(JoinPoint jp){

        Object[] args= jp.getArgs();
        if(args!=null&&args.length>0){
            for(Object o:args){
                Annotation[] an=o.getClass().getAnnotations();
                if(an!=null&&an.length>0){
                    for(Annotation a:an){
                        if(a.equals("Update")){

                        }
                    }
                }
            }
        }
    }
}
