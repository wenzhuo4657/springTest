package org.example.spring_aop;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setName("SpringUser");
        return user;
    }

    // Enable auto-proxying and force CGLIB proxies (no @Aspect usage)
    @Bean
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true); // force CGLIB
        return creator;
    }

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Bean
    public DefaultPointcutAdvisor userAdvisor() {
        // Pointcut for all methods in our User class
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* org.example.spring_aop.User.*(..))");
        return new DefaultPointcutAdvisor(pointcut, loggingInterceptor());
    }
}
