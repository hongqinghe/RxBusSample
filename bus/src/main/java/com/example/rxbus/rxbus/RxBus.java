package com.example.rxbus.rxbus;


import com.example.rxbus.rxbus.thread.ThreadEnforcer;

/***********************************************
 * <P> 封装Rxbus单例的类，对外暴露
 * * <P> Author: gongtong
 * <P> Date: 2017-06-26 上午11:04
 * <P> Copyright  2008 二维火科技
 ***********************************************/

public class RxBus {
    private static Bus busInstance;

    public static synchronized Bus getInstance() {
        if (busInstance == null) {
            busInstance = new Bus(ThreadEnforcer.ANY);
        }
        return busInstance;
    }
}
