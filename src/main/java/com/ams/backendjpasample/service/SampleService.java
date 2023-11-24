package com.ams.backendjpasample.service;

import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.dto.response.MemberResDto;
import com.ams.backendjpasample.dto.response.TeamResDto;
import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import org.springframework.data.domain.Page;

public interface SampleService {

    AmsResDto getHealthCheck();
    Page<Member> getMembers(MemberReqDto memberReqDto);
    Page<Team> getTeams(TeamReqDto teamReqDto);
    MemberResDto getMemberById(Long id);
    TeamResDto getTeamById(Long id);
    MemberResDto saveMember(MemberReqDto memberReqDto);
    TeamResDto saveTeam(TeamReqDto teamReqDto);
    MemberResDto updateMember(MemberReqDto memberReqDto);
    TeamResDto updateTeam(TeamReqDto teamReqDto);
}
