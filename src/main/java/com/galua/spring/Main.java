package com.galua.spring;

import com.galua.spring.service.TestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.galua.spring");
        TestService testServiceBean = context.getBean(TestService.class);
        System.out.println(testServiceBean.test());
    }
}
