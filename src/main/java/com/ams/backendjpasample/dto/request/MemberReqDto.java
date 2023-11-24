package com.ams.backendjpasample.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import com.ams.backendjpasample.entity.Team;
@Getter
@Setter
@Data
public class MemberReqDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String joinedDate;
    private Long teamId;
    private Team team;

    // page options
    private int pageNumber;
    private int pageSize;

    // filters
    private String firstNameFilter;
    private String joinedDateFilter;
    private String lastNameFilter;
    private String teamIdFilter;

    // search
    private String searchString;

    @Override
    public String toString() {
        return "MemberReqDto{" +
                "id=" + id +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", firstNameFilter='" + firstNameFilter + '\'' +
                ", joinedDateFilter='" + joinedDateFilter + '\'' +
                ", lastNameFilter='" + lastNameFilter + '\'' +
                ", teamIdFilter='" + teamIdFilter + '\'' +
                ", searchString='" + searchString + '\'' +
                '}';
    }
}
