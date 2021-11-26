package com.galua.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AutowireConfiguration {

    @Bean
    @Scope(value = "prototype")
    public String aConfig() {
        return "a";
    }

    @Bean
    public String bConfig() {
        return "b";
    }
}
