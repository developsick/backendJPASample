package com.ams.backendjpasample.repository.spec;

import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TeamSpec {

    private static final Logger log = LoggerFactory.getLogger(TeamSpec.class);

    public static Specification<Team> selectData(TeamReqDto teamReqDto) {
        return new Specification<Team>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                log.debug("TeamSpec.selectData.toPredicate start");
                List<Predicate> predicates = new ArrayList<>();

                // 필터링 조건 추가
                if( ObjectUtils.isNotEmpty(teamReqDto.getName()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), teamReqDto.getName())));
                }
                if( ObjectUtils.isNotEmpty(teamReqDto.getLocation()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("location"), teamReqDto.getLocation())));
                }
                if( ObjectUtils.isNotEmpty(teamReqDto.getFoundedDate()) ) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("foundedDate"), teamReqDto.getFoundedDate())));
                }

                // 키워드 검색 조건 추가
                if( ObjectUtils.isNotEmpty(teamReqDto.getSearchString()) ) {
                    // create 2 like predicates
                    Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + teamReqDto.getSearchString() + "%");
                    Predicate locationPredicate = criteriaBuilder.like(root.get("location"), "%" + teamReqDto.getSearchString() + "%");
                    // combine predicates with "or"
                    predicates.add(criteriaBuilder.or(namePredicate, locationPredicate));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
