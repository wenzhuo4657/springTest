package org.example;




import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.Factory;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new MyMethodInterceptor());
        User user=(User)enhancer.create();

        user.setName("张三");

        user.hello();


        Factory factory=(Factory)user;
        User user1=(User)factory.newInstance(factory.getCallbacks());
        user1.setName("李四");
        user1.hello();

    }




}