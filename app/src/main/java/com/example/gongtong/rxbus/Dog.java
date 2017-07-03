package com.example.gongtong.rxbus;


import com.example.rxbus.rxbus.annotation.Subscribe;
import com.example.rxbus.rxbus.annotation.Tag;

/***********************************************
 * <P> dec:
 * <P> Author: gongtong
 * <P> Date: 17-6-29.
 * <P> Copyright  2008 二维火科技
 ***********************************************/

public class Dog {

    String name;
    String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Subscribe(tags = @Tag("dog"))
    public void setDogName(String name){
        this.name=name;
        System.out.println("小狗的名字"+this.name);
    }


}
