package com.ams.backendjpasample.entity;

import com.ams.backendjpasample.dto.request.MemberReqDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "JOINED_DATE", nullable = false)
    private String joinedDate;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "TEAM_ID", nullable = false)
    private String teamId;

    public Member(MemberReqDto memberReqDto) {
        this.id = memberReqDto.getId();
        this.firstName = memberReqDto.getFirstName();
        this.joinedDate = memberReqDto.getJoinedDate();
        this.lastName = memberReqDto.getLastName();
        this.teamId = memberReqDto.getTeamId();
    }


}