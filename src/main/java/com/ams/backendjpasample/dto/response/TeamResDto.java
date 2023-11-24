package com.ams.backendjpasample.dto.response;

import com.ams.backendjpasample.entity.Member;
import com.ams.backendjpasample.entity.Team;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class TeamResDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String location;
    private String foundedDate;
    private List<Member> members;

    public TeamResDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.location = team.getLocation();
        this.foundedDate = team.getFoundedDate();
        this.members = new ArrayList<>();

        if(team.getMembers() != null && team.getMembers().size() > 0){
            for(Member member: team.getMembers()){
                members.add(member);
            }
        }
    }
}
