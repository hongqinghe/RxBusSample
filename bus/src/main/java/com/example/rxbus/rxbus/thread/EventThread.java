package com.example.rxbus.rxbus.thread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/***********************************************
 * <P> 线程枚举类  （用于切换线程操作哦）
 * <P> Author: gongtong
 * <P> Date: 2017-06-26 上午11:04
 * <P> Copyright  2008 二维火科技
 ***********************************************/

public enum EventThread {
    /**
     * thread is UI thread
     */
    MAIN_THREAD,
    /**
     * 创建一个新的线程
     */
    NEW_THREAD,
    /**
     * 在IO线程中，用于IO密集型任务，如异步阻塞IO操作，这个调度器的线程池会根据需要增长
     */
    IO,
    /**
     * 用于计算任务，如事件循环和回调处理，不要用于IO操作；（默认线程数等于处理器的数量）
     */
    COMPUTATION,
    /**
     * 当其他排队的任务完成后，在当前线程排队开始执行
     */
    TRAMPOLINE,
    /**
     * 在指定的一个单线程中执行
     */
    SINGLE,
    /**
     * 用于指定的executor作为调度器
     */
    EXECUTOR,
    /**
     *用于在指定的handler的线程作为调度器
     */
    HANDLER;
    public  static Scheduler getScheduler(EventThread thread){
        Scheduler schedulers;
        switch (thread){
            case MAIN_THREAD:
                schedulers = AndroidSchedulers.mainThread();
                break;
            case NEW_THREAD:
                schedulers = Schedulers.newThread();
                break;
            case IO:
                schedulers=Schedulers.io();
                break;
            case COMPUTATION:
                schedulers = Schedulers.computation();
                break;
            case TRAMPOLINE:
                schedulers = Schedulers.trampoline();
                break;
            case SINGLE:
                schedulers=Schedulers.single();
                break;
            case EXECUTOR:
                schedulers = Schedulers.from(ThreadHandler.DEFAULT.getExecutor());
                break;
            case HANDLER:
                schedulers = AndroidSchedulers.from(ThreadHandler.DEFAULT.getHandler().getLooper());
                break;
            default:
                schedulers=AndroidSchedulers.mainThread();
                break;
        }
        return schedulers;
    }
}
