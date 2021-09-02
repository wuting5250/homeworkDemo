package com.example.course.week3.orm.demo4.aop;

import java.lang.reflect.Method;

public interface MethodInvocation {
    Object proceed() throws Throwable;
    Method getMethod();
    Object[] getArguments();
    Object getTarget();
}
