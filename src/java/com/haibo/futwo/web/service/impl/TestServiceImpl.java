package com.haibo.futwo.web.service.impl;

import com.haibo.futwo.web.mappers.TestMapper;
import com.haibo.futwo.web.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    static final Logger  logger= LoggerFactory.getLogger("commonSchedulerLogger");
    @Autowired
    private TestMapper testMapper;

    @Override
    public Integer getSumSalary() {
        int num = testMapper.getSumSalary();
        return num;
    }
}
