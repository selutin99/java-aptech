package com.galua.spring.service.impl;

import com.galua.spring.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test() {
        return "42";
    }
}
