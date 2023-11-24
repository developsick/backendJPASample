package com.ams.backendjpasample.service.impl;

import com.ams.backendjpasample.common.enums.CommonError;
import com.ams.backendjpasample.common.exceptions.BizRuntimeException;
import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.dto.response.MemberResDto;
import com.ams.backendjpasample.dto.response.TeamResDto;
import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import com.ams.backendjpasample.repository.MemberRepository;
import com.ams.backendjpasample.repository.TeamRepository;
import com.ams.backendjpasample.repository.spec.MemberSpec;
import com.ams.backendjpasample.repository.spec.TeamSpec;
import com.ams.backendjpasample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service("SampleService")
@RequiredArgsConstructor
public class SampleServiceImpl implements SampleService {

    private static final Logger log = LoggerFactory.getLogger(SampleServiceImpl.class);

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;


    public AmsResDto getHealthCheck(){

        log.info("SampleServiceImpl.getHealthCheck start");

        return new AmsResDto("정상", 200L);
    }

    public Page<Member> getMembers(MemberReqDto memberReqDto){

        log.info("SampleServiceImpl.getMembers start");

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "joinedDate"));

        Pageable pageable = PageRequest.of(memberReqDto.getPageNumber() - 1, memberReqDto.getPageSize(), Sort.by("id").ascending());
        return memberRepository.findAll(MemberSpec.selectData(memberReqDto), pageable);
    }

    public Page<Team> getTeams(TeamReqDto teamReqDto){
        log.info("SampleServiceImpl.getTeams start");

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "foundedDate"));

        Pageable pageable = PageRequest.of(teamReqDto.getPageNumber() - 1, teamReqDto.getPageSize(), Sort.by("id").ascending());
        Page<Team> res = teamRepository.findAll(TeamSpec.selectData(teamReqDto), pageable);

        return res;
    }

    public MemberResDto getMemberById(Long id){
        log.info("SampleServiceImpl.getMember start");

        Optional<Member> res = memberRepository.findById(id);
        if(res.isEmpty()){
            throw new BizRuntimeException(CommonError.DATA_NOT_FOUND);
        }
        return new MemberResDto(res.get());
    }

    public TeamResDto getTeamById(Long id){
        log.info("SampleServiceImpl.getTeamById start");

        Optional<Team> res = teamRepository.findById(id);
        if(res.isEmpty()){
            throw new BizRuntimeException(CommonError.DATA_NOT_FOUND);
        }
        return new TeamResDto(res.get());
    }

    public MemberResDto saveMember(MemberReqDto memberReqDto){
        log.info("SampleServiceImpl.saveMember start");

        Member member = new Member(memberReqDto);
        Member res = memberRepository.save(member);
        return new MemberResDto(res);
    }

    public TeamResDto saveTeam(TeamReqDto teamReqDto){
        log.info("SampleServiceImpl.saveTeam start");

        Team team = new Team(teamReqDto);

        log.info("team = " + team.toString());
        Team res = teamRepository.save(team);
        return new TeamResDto(res);
    }



    public MemberResDto updateMember(MemberReqDto memberReqDto){
        log.info("SampleServiceImpl.updateMember start");

        log.info("memberReqDto = " + memberReqDto.toString());

        Optional<Member> member = memberRepository.findById(memberReqDto.getId());
        if(member.isEmpty()){
            throw new BizRuntimeException(CommonError.DATA_NOT_FOUND);
        }

        Member updatingMember = member.get();
        if(ObjectUtils.isNotEmpty(memberReqDto.getFirstName())) updatingMember.setFirstName(memberReqDto.getFirstName());
        if(ObjectUtils.isNotEmpty(memberReqDto.getLastName())) updatingMember.setLastName(memberReqDto.getLastName());
        if(ObjectUtils.isNotEmpty(memberReqDto.getAddress())) updatingMember.setLastName(memberReqDto.getAddress());
        if(ObjectUtils.isNotEmpty(memberReqDto.getJoinedDate())) updatingMember.setJoinedDate(memberReqDto.getJoinedDate());
        if(ObjectUtils.isNotEmpty(memberReqDto.getTeamId())) updatingMember.setTeam(new Team(memberReqDto.getTeamId()));

        Member res = memberRepository.save(updatingMember);
        return new MemberResDto(res);
    }

    public TeamResDto updateTeam(TeamReqDto teamReqDto){
        log.info("SampleServiceImpl.updateMember start");

        log.info("teamReqDto = " + teamReqDto.toString());

        Optional<Team> team = teamRepository.findById(teamReqDto.getId());
        if(team.isEmpty()){
            throw new BizRuntimeException(CommonError.DATA_NOT_FOUND);
        }

        Team updatingTeam = team.get();
        if(ObjectUtils.isNotEmpty(teamReqDto.getName())) updatingTeam.setName(teamReqDto.getName());
        if(ObjectUtils.isNotEmpty(teamReqDto.getLocation())) updatingTeam.setLocation(teamReqDto.getLocation());
        if(ObjectUtils.isNotEmpty(teamReqDto.getFoundedDate())) updatingTeam.setFoundedDate(teamReqDto.getFoundedDate());

        Team res = teamRepository.save(updatingTeam);
        return new TeamResDto(res);
    }

}
