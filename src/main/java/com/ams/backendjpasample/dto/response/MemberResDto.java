package com.ams.backendjpasample.dto.response;

import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@Data
public class MemberResDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String joinedDate;
    private Long teamId;
    private Team team;

    public MemberResDto(Member member) {
        this.id = member.getId();
        this.firstName = member.getFirstName();
        this.lastName = member.getLastName();
        this.address = member.getAddress();
        this.joinedDate = member.getJoinedDate();
        this.teamId = member.getTeam().getId();
        this.team = member.getTeam();
    }

    @Override
    public String toString() {
        String teamString = "";
        if(team != null){
            teamString = team.toString();
        }

        return "MemberResDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", joinedDate='" + joinedDate + '\'' +
                ", team='" + teamString + '\'' +
                '}';
    }
}
