package org.example.test01;




import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;


public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);

        Callback[] callbacks = new Callback[] {
                new MyMethodInterceptor(),
                new MyLazyLoader()
        };

        enhancer.setCallbackFilter(method -> {
            return 1;
        });

        enhancer.setCallbacks(callbacks);
        User user=(User)enhancer.create();
        user.hello();
    }




}