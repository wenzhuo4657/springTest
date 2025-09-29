package org.example.test02;


import org.springframework.cglib.proxy.Enhancer;


public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new MyMethodInterceptor());

        User user=(User)enhancer.create();
        user.hello();

    }




}