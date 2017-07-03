package com.example.gongtong.rxbus;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;


import com.example.rxbus.rxbus.RxBus;
import com.example.rxbus.rxbus.annotation.Produce;
import com.example.rxbus.rxbus.annotation.Subscribe;
import com.example.rxbus.rxbus.annotation.Tag;
import com.example.rxbus.rxbus.thread.EventThread;

import java.util.Date;


/***********************************************
 * <P> RxBusDemo    一些Rxbus的常用示例
 * <P> Author: gongtong
 * <P> Date: 2017-06-29 下午5:18
 * <P> Copyright  2008 二维火科技
 ***********************************************/
public class RxBusDemoActivity extends FragmentActivity {

    private TextView rxContent, rxContent2, rxContent3, rxContent4, rxContent5, rxContent6;
    private Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus_demo);
        rxContent = (TextView) findViewById(R.id.rxContent);
        rxContent2 = (TextView) findViewById(R.id.rxContent2);
        rxContent3 = (TextView) findViewById(R.id.rxContent3);
        rxContent4 = (TextView) findViewById(R.id.rxContent4);
        rxContent5 = (TextView) findViewById(R.id.rxContent5);
        rxContent6 = (TextView) findViewById(R.id.rxContent6);
        dog = new Dog();
        //注册Rxbus
        RxBus.getInstance().register(this);


    }

    public void sendRxBus(View view) {
        int i = 100;
        while (i > 1) {
            RxBus.getInstance().post("test" + i + new Date());
            i--;
        }
    }

    public void sendRxBus2(View view) {
        RxBus.getInstance().post("tag", "test2");
    }

    @Subscribe(thread = EventThread.HANDLER)
    public void onEvent(String content) {
//        rxContent.setText("接收到了消息为" + content);
        rxContent.setText(content);
        System.out.println(content);
    }


    @Subscribe(tags = @Tag("tag"))
    public void onEvent2(String content) {
        rxContent2.setText("接收到了消息为" + content);
    }


    /**
     * 使用了@Produce 发送了事件，但是没有接收者，这里注册接收者，事件即可发送
     *
     * @param view
     */
    public void sendRxBus3(View view) {
        boolean register = RxBus.getInstance().isRegister(dog);
        if (!register) {
            RxBus.getInstance().register(dog);
        }

    }

    /**
     * 总结：在tags相同的情况下，会匹配方法的返回值，只有两个都匹配的情况下才能进行事件的处理接收
     * 使用@Produce注解可以进行事件的发送 ，但是前提必须是有返回值的方法
     *
     * @return
     */
    @Produce(tags = {@Tag("直接发送数据")})
    public String test() {
        return "hello";
    }


    @Produce(tags = {@Tag("dog")})
    public String toName() {
        return "旺旺";
    }

    @Produce(thread = EventThread.NEW_THREAD, tags = {@Tag("直接发送数据2")})
    public boolean test2() {
        System.out.println(Thread.currentThread().getName() + "------------------------------------------");
        return false;
    }
    //不能重复定义相同事件
//    @Produce(thread = EventThread.NEW_THREAD, tags = {@Tag("直接发送数据2")})
//    public boolean test88() {
//        return false;
//    }

    @Produce(thread = EventThread.NEW_THREAD, tags = {@Tag("int")})
    public int test3() {
        return 0;
    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag("int")})
    public void OnEvent(int content) {
        rxContent3.setText("我接受int值：：：： count————>" + content);
    }


    @Subscribe(tags = {@Tag("直接发送数据")})
    public void OnEvent2(String content) {
        rxContent4.setText("我接收到数据了        :" + content);
    }

    @Subscribe(tags = {@Tag("直接发送数据2")}, thread = EventThread.MAIN_THREAD)
    public void OnEvent3(boolean content) {
        System.out.println(Thread.currentThread().getName() + "------------------------------------------");
        rxContent5.setText("我接收到数据了       :" + content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unregister(this);
        if (RxBus.getInstance().isRegister(dog)) {
            RxBus.getInstance().unregister(dog);
        }
    }

}
