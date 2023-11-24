package com.ams.backendjpasample.entity;

import com.ams.backendjpasample.dto.request.MemberReqDto;
import com.ams.backendjpasample.dto.response.MemberResDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "JOINED_DATE", nullable = false)
    private String joinedDate;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    public Member(MemberReqDto memberReqDto) {
        this.id = memberReqDto.getId();
        this.firstName = memberReqDto.getFirstName();
        this.lastName = memberReqDto.getLastName();
        this.address = memberReqDto.getAddress();
        this.joinedDate = memberReqDto.getJoinedDate();
        this.team = new Team(memberReqDto.getTeamId());
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", joinedDate='" + joinedDate + '\'' +
                ", team=" + team.toString() +
                '}';
    }
}