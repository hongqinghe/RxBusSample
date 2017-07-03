package com.example.rxbus.rxbus.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/***********************************************
 * <P> 在线程操作切换时的工具类，用于创建线程池和handler的操作
 * <P> Author: gongtong
 * <P> Date: 2017-06-26 上午11:09
 * <P> Copyright  2008 二维火科技
 ***********************************************/
 interface ThreadHandler {
    Executor getExecutor();

    Handler getHandler();

    ThreadHandler DEFAULT = new ThreadHandler() {
        private Executor executor;
        private Handler handler;

        @Override
        public Executor getExecutor() {
            if (executor == null) {
                //创建一个可缓存的线程池
                executor = Executors.newCachedThreadPool();
            }
            return executor;
        }

        @Override
        public Handler getHandler() {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            return handler;
        }
    };
}
