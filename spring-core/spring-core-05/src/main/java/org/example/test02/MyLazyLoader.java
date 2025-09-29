package org.example.test02;

import org.springframework.cglib.proxy.LazyLoader;

public class MyLazyLoader implements LazyLoader {
    @Override
    public Object loadObject() throws Exception {
        User user = new User();
        user.setName("张三");
        return user;
    }
}
