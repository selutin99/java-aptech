package com.galua.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public String aConfig() {
        return "a";
    }

    @Bean
    public String bConfig() {
        return "b";
    }
}
