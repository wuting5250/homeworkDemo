package com.example.course.week3.orm.demo4.aop.advice.interceptor;

import com.example.course.week3.orm.demo4.aop.advice.Advice;
import com.example.course.week3.orm.demo4.aop.MethodInvocation;

public interface MethodInterceptor extends Advice {
    Object invoke(MethodInvocation mi) throws Throwable;
}
