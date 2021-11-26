package com.galua.spring;

import com.galua.spring.service.AutowiredService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.galua.spring");

        // Get beans from configuration class and autowire config beans into service bean
        AutowiredService autowiredServiceBean = context.getBean(AutowiredService.class);
        System.out.println(autowiredServiceBean.testMethod());

        // Get singleton bean from component by name
        System.out.println(context.getBean("test", String.class));

        // Get prototype bean
        String[] testPrototype1 = (String[]) context.getBean("prototypeTest");
        String[] testPrototype2 = (String[]) context.getBean("prototypeTest");
        System.out.println(testPrototype1 == testPrototype2);

        // Get singleton bean
        String[] testSingleton1 = (String[]) context.getBean("singletonTest");
        String[] testSingleton2 = (String[]) context.getBean("singletonTest");
        System.out.println(testSingleton1 == testSingleton2);
    }
}
