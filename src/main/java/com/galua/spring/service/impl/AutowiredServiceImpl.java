package com.galua.spring.service.impl;

import com.galua.spring.service.AutowiredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutowiredServiceImpl implements AutowiredService {

    @Autowired
    private String aConfig;

    @Autowired
    private String bConfig;

    @Override
    public String aMethod() {
        return aConfig;
    }

    @Override
    public String bMethod() {
        return bConfig;
    }
}
