package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.example.AspectJ.Main;
import org.example.AspectJ.User;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UserProxyTest {

    @Test
    void aroundAdviceWrapsUser() {
//        todo 待研究这个断言测试
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class)) {
            User user = context.getBean(User.class);
            assertTrue(AopUtils.isAopProxy(user), "User bean should be proxied");
            assertTrue(AopUtils.isCglibProxy(user), "Proxy should be forced to use CGLIB");
            assertFalse(AopUtils.isJdkDynamicProxy(user), "Proxy should not be JDK dynamic proxy");
            String name = user.getName();
            assertEquals("SpringUser", name);
        } finally {
            System.setOut(original);
        }
        String console = out.toString(StandardCharsets.UTF_8);
        assertTrue(console.contains("UserProxy around before"), "Advice should run before method invocation");
        assertTrue(console.contains("UserProxy around after"), "Advice should run after method invocation");
    }
}
