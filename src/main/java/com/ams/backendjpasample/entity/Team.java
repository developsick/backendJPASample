package com.ams.backendjpasample.entity;

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

    @Column(name = "FOUNDED_DATE", nullable = false)
    private String foundedDate;

    @Column(name = "LOCATION", nullable = false)
    private String location;

    @Column(name = "MASCOT", nullable = false)
    private String mascot;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "ID", referencedColumnName = "TEAM_ID", insertable=false, updatable=false),
    })
    private Member member;
}
