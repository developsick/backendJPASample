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
    private String foundedDate;
    private String location;
    private String mascot;
    private String name;
    private List<Member> members;

    public TeamResDto(Team team) {
        this.id = team.getId();
        this.foundedDate = team.getFoundedDate();
        this.location = team.getLocation();
        this.mascot = team.getMascot();
        this.name = team.getName();
        this.members = new ArrayList<>();

        // TODO: member List 처리
    }
}
