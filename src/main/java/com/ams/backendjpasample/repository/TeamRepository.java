package com.ams.backendjpasample.repository;

import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long>, JpaSpecificationExecutor<Team>{
    List<Team> findAllById(Long id);
}
