package org.example;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Factory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true) // 强制使用CGLIB
@ComponentScan("org.example")
public class Main {

    @Bean
    public User user() {
        User user = new User();
        user.setName("SpringUser");
        return user;
    }

    @Bean
    public UserProxy userProxy() {
        return new UserProxy();
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            User user = context.getBean(User.class);
            user.show();


            Factory factory =(Factory)  user;
            Callback[]  callbacks=  ((Factory) user).getCallbacks();
            for (Callback callback:callbacks){
                System.out.println(callback);
            }


        }
    }
}
