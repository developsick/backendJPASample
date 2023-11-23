package com.ams.backendjpasample.dto.response;

import com.ams.backendjpasample.entity.Member;
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
    private String joinedDate;
    private String lastName;
    private String teamId;

    public MemberResDto(Member member) {
        this.id = member.getId();
        this.firstName = member.getFirstName();
        this.joinedDate = member.getJoinedDate();
        this.lastName = member.getLastName();
        this.teamId = member.getTeamId();
    }

    @Override
    public String toString() {
        return "MemberResDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", joinedDate='" + joinedDate + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
