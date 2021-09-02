package com.example.course.week3.orm.demo4;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MapperAspect {
    @Around("execution(* com.example.course.week3.orm.demo4.StuMapperImpl.convert(..))")
    public void logTransformation(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Beginning object mapping: " + pjp.getSignature().getName());
        pjp.proceed();
        System.out.println("Finished object mapping: " + pjp.getSignature().getName());
    }

}
