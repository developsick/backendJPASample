package com.ams.backendjpasample.service;

import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.dto.response.MemberResDto;
import com.ams.backendjpasample.dto.response.TeamResDto;
import org.springframework.data.domain.Page;

public interface SampleService {

    AmsResDto getHealthCheck();
    Page<MemberResDto> getMembers(MemberReqDto memberReqDto);
    Page<TeamResDto> getTeams(TeamReqDto teamReqDto);
    MemberResDto getMemberById(Long id);
    MemberResDto saveMember(MemberReqDto memberReqDto);
    MemberResDto updateMember(MemberReqDto memberReqDto);
}
