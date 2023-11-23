package com.ams.backendjpasample.service.impl;

import com.ams.backendjpasample.common.enums.CommonError;
import com.ams.backendjpasample.common.exceptions.BizRuntimeException;
import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.dto.response.AmsResDto;
import com.ams.backendjpasample.dto.response.MemberResDto;
import com.ams.backendjpasample.dto.response.TeamResDto;
import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.repository.MemberRepository;
import com.ams.backendjpasample.repository.spec.MemberSpec;
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



    public AmsResDto getHealthCheck(){

        log.info("SampleServiceImpl.getHealthCheck start");

        return new AmsResDto("정상", 200L);
    }

    public Page<MemberResDto> getMembers(MemberReqDto memberReqDto){

        log.info("SampleServiceImpl.getMembers start");

        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.ASC, "joinedDate"));

        Pageable pageable = PageRequest.of(memberReqDto.getPageNumber() - 1, memberReqDto.getPageSize(), Sort.by("tblNm").ascending());
        Page<Member> res = memberRepository.findAll(MemberSpec.selectData(memberReqDto), pageable);

        Page<MemberResDto> result = res.map(new Function<Member, MemberResDto>() {
            @Override
            public MemberResDto apply(Member member) {
                MemberResDto dto = new MemberResDto(member);

                return dto;
            }
        });

        return result;
    }

    public Page<TeamResDto> getTeams(TeamReqDto teamReqDto){



        return null;
    }







    public MemberResDto getMemberById(Long id){
        log.info("SampleServiceImpl.getMember start");

        Optional<Member> res = memberRepository.findById(id);
        if(res.isEmpty()){
            throw new BizRuntimeException(CommonError.DATA_NOT_FOUND);
        }
        return new MemberResDto(res.get());
    }

    public MemberResDto saveMember(MemberReqDto memberReqDto){
        log.info("SampleServiceImpl.saveMember start");

        log.info("memberReqDto = " + memberReqDto.toString());
        Member member = new Member(memberReqDto);
        Member res = memberRepository.save(member);
        return new MemberResDto(res);
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
        if(ObjectUtils.isNotEmpty(memberReqDto.getJoinedDate())) updatingMember.setJoinedDate(memberReqDto.getJoinedDate());
        if(ObjectUtils.isNotEmpty(memberReqDto.getLastName())) updatingMember.setLastName(memberReqDto.getLastName());
        if(ObjectUtils.isNotEmpty(memberReqDto.getTeamId())) updatingMember.setTeamId(memberReqDto.getTeamId());

        Member res = memberRepository.save(updatingMember);
        return new MemberResDto(res);
    }



}
