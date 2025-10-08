package org.example.spring_aop;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Factory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            User user = context.getBean(User.class);
            user.show();

            if (user instanceof Factory) {
                Callback[] callbacks = ((Factory) user).getCallbacks();
                for (Callback callback : callbacks) {
                    System.out.println(callback);
                }
            }
        }
    }
}

