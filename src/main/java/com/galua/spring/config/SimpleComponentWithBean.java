package com.galua.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SimpleComponentWithBean {

    @Bean(name = "test")
    public String test2() {
        return "test2";
    }
}
