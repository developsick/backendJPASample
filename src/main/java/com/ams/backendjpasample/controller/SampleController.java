package com.ams.backendjpasample.controller;


import com.ams.backendjpasample.common.dto.ResMsgDto;
import com.ams.backendjpasample.common.dto.ResPagingDto;
import com.ams.backendjpasample.common.enums.CommonError;
import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.dto.response.MemberResDto;
import com.ams.backendjpasample.dto.response.TeamResDto;
import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import com.ams.backendjpasample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SampleController {

    private static final Logger log = LoggerFactory.getLogger(SampleController.class);
    private final SampleService sampleService;

    @GetMapping("/healthCheck")
    public ResponseEntity<ResMsgDto<AmsResDto>> getHealthCheck(){

        log.info("AmsController.getHealthCheck start");

        AmsResDto result = sampleService.getHealthCheck();

        ResMsgDto<AmsResDto> response = new ResMsgDto<>(CommonError.SUCCESS);
        response.setBody(result);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<ResMsgDto<ResPagingDto<MemberResDto>>> getMembers(
            @RequestBody MemberReqDto memberReqDto){
        log.debug("SampleController.getMembers start");

        //Page<MemberResDto> pageableList = sampleService.getMembers(memberReqDto);
        Page<Member> pageableList = sampleService.getMembers(memberReqDto);

        ResMsgDto<ResPagingDto<MemberResDto>> response = new ResMsgDto<>(CommonError.SUCCESS);
        response.setPageableBody(pageableList, Member.class, MemberResDto.class);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PostMapping("/teams")
    public ResponseEntity<ResMsgDto<ResPagingDto<TeamResDto>>> getTeams(
            @RequestBody TeamReqDto teamReqDto){
        log.debug("SampleController.getTeams start");

        Page<Team> pageableList = sampleService.getTeams(teamReqDto);

        ResMsgDto<ResPagingDto<TeamResDto>> response = new ResMsgDto<>(CommonError.SUCCESS);
        response.setPageableBody(pageableList, Team.class, TeamResDto.class);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<ResMsgDto<MemberResDto>> getMemberById(
            @PathVariable(name = "id") Long id){

        log.debug("SampleController.getMemberById start");

        MemberResDto res = sampleService.getMemberById(id);

        ResMsgDto<MemberResDto> response = new ResMsgDto<>(CommonError.SUCCESS);
        response.setBody(res);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<ResMsgDto<TeamResDto>> getTeamById(
            @PathVariable(name = "id") Long id){

        log.debug("SampleController.getTeamById start");

        TeamResDto res = sampleService.getTeamById(id);

        ResMsgDto<TeamResDto> response = new ResMsgDto<>(CommonError.SUCCESS);
        response.setBody(res);
        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PutMapping("/member")
    public ResponseEntity<ResMsgDto<MemberResDto>> saveMember(
            @RequestBody MemberReqDto memberReqDto){

        log.debug("SampleController.saveMember start");

        MemberResDto res = sampleService.saveMember(memberReqDto);
        ResMsgDto<MemberResDto> response = new ResMsgDto<>();
        response.setBody(res);

        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PutMapping("/team")
    public ResponseEntity<ResMsgDto<TeamResDto>> saveTeam(
            @RequestBody TeamReqDto teamReqDto){

        log.debug("SampleController.saveTeam start");

        TeamResDto res = sampleService.saveTeam(teamReqDto);
        ResMsgDto<TeamResDto> response = new ResMsgDto<>();
        response.setBody(res);

        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PatchMapping("/member")
    public ResponseEntity<ResMsgDto<MemberResDto>> updateMember(
            @RequestBody MemberReqDto memberReqDto){

        log.debug("SampleController.updateMember start");

        MemberResDto res = sampleService.updateMember(memberReqDto);
        ResMsgDto<MemberResDto> response = new ResMsgDto<>();
        response.setBody(res);

        return new ResponseEntity<>( response, HttpStatus.OK);
    }

    @PatchMapping("/team")
    public ResponseEntity<ResMsgDto<TeamResDto>> updateTeam(
            @RequestBody TeamReqDto teamReqDto){

        log.debug("SampleController.updateTeam start");

        TeamResDto res = sampleService.updateTeam(teamReqDto);
        ResMsgDto<TeamResDto> response = new ResMsgDto<>();
        response.setBody(res);

        return new ResponseEntity<>( response, HttpStatus.OK);
    }

}
