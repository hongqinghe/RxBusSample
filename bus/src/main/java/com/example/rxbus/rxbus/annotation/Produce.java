package com.example.rxbus.rxbus.annotation;


import com.example.rxbus.rxbus.thread.EventThread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/***********************************************
 * <P> dec:    用于被观察者事件注解
 * <P> Author: gongtong
 * <P> Date: 17-6-24.
 * <P> Copyright  2008 二维火科技
 ***********************************************/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Produce {
    Tag[] tags() default {};

    EventThread thread() default EventThread.MAIN_THREAD;
}
