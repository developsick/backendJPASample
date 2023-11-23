package com.ams.backendjpasample.service.impl;

import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.service.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("SampleService")
public class SampleServiceImpl implements SampleService {

    private static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);
    public AmsResDto getHealthCheck(){

        log.info("ExecutionServiceImpl.getHealthCheck start");
        return new AmsResDto("정상", 200L);
    }
}
