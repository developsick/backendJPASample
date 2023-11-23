package com.ams.backendjpasample.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@Data
public class MemberReqDto {

    private Long id;
    private String firstName;
    private String joinedDate;
    private String lastName;
    private String teamId;

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
