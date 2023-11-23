package com.ams.backendjpasample.repository.spec;

import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MemberSpec {

    private static final Logger log = LoggerFactory.getLogger(MemberSpec.class);

    public static Specification<Member> selectData(MemberReqDto memberReqDto) {
        return new Specification<Member>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                log.debug("TableMetaInfoSpec.selectData.toPredicate start");
                List<Predicate> predicates = new ArrayList<>();

                // 필터링 조건 추가
                if( ObjectUtils.isNotEmpty(memberReqDto.getFirstNameFilter()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("firstName"), memberReqDto.getFirstNameFilter())));
                }
                if( ObjectUtils.isNotEmpty(memberReqDto.getJoinedDateFilter()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("joinedData"), memberReqDto.getJoinedDateFilter())));
                }
                if( ObjectUtils.isNotEmpty(memberReqDto.getLastNameFilter()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lastName"), memberReqDto.getLastNameFilter())));
                }
                if( ObjectUtils.isNotEmpty(memberReqDto.getTeamIdFilter()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("teamId"), memberReqDto.getTeamIdFilter())));
                }

                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("dataSource").get("deleted"), false )));

                // 키워드 검색 조건 추가
                if( ObjectUtils.isNotEmpty(memberReqDto.getSearchString()) ) {
                    // create 2 like predicates
                    Predicate firstNamePredicate = criteriaBuilder.like(root.get("firstName"), "%" + memberReqDto.getSearchString() + "%");
                    Predicate lastNamePredicate = criteriaBuilder.like(root.get("lastName"), "%" + memberReqDto.getSearchString() + "%");
                    // combine predicates with "or"
                    predicates.add(criteriaBuilder.or(firstNamePredicate, lastNamePredicate));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
