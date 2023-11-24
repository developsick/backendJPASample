package com.ams.backendjpasample.entity;

import com.ams.backendjpasample.dto.request.TeamReqDto;
import com.ams.backendjpasample.dto.response.TeamResDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "FOUNDED_DATE", nullable = false)
    private String foundedDate;

    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="team", fetch = FetchType.LAZY)
    private List<Member> members;

    public Team(TeamReqDto teamReqDto) {
        this.id = teamReqDto.getId();
        this.name = teamReqDto.getName();
        this.location = teamReqDto.getLocation();
        this.foundedDate = teamReqDto.getFoundedDate();
    }

    public Team(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", foundedDate='" + foundedDate + '\'' +
                '}';
    }
}
