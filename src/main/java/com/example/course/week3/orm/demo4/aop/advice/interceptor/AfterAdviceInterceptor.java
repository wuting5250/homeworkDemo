package com.example.course.week3.orm.demo4.aop.advice.interceptor;

import com.example.course.week3.orm.demo4.aop.advice.BeforeAfterAdvice;
import com.example.course.week3.orm.demo4.aop.MethodInvocation;

public class AfterAdviceInterceptor implements MethodInterceptor {
    private BeforeAfterAdvice advice;

    public AfterAdviceInterceptor(BeforeAfterAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        Object returnValue = mi.proceed();
        advice.execute(mi.getMethod(), mi.getArguments(), mi.getTarget());
        return returnValue;
    }

}
