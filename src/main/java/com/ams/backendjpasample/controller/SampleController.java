package com.ams.backendjpasample.controller;


import com.ams.backendjpasample.common.dto.ResMsgDto;
import com.ams.backendjpasample.common.enums.CommonError;
import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/healthCheck")
    public ResponseEntity<ResMsgDto<AmsResDto>> getHealthCheck(){

        log.info("AmsController.getHealthCheck start");

        AmsResDto result = sampleService.getHealthCheck();

        ResMsgDto<AmsResDto> response = new ResMsgDto<>(CommonError.SUCCESS);
        response.setBody(result);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }





}
