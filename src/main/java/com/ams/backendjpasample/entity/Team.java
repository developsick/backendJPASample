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

    @Column(name = "NAME")
    private String name;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "FOUNDED_DATE")
    private String foundedDate;

    @OneToMany
    private List<Member> members = new ArrayList<>();
}
