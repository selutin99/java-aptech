package com.galua.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Scopes {

    @Bean
    @Scope(value = "prototype")
    public String[] prototypeTest() {
        return new String[]{"test1", "test2"};
    }

    @Bean
    public String[] singletonTest() {
        return new String[]{"test1", "test2"};
    }
}
