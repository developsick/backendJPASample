package com.ams.backendjpasample.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TeamReqDto {

    private Long id;
    private String name;
    private String location;
    private String foundedDate;

    // page options
    private int pageNumber;
    private int pageSize;

    // filters
    private String nameFilter;
    private String locationFilter;
    private String foundedDateFilter;

    // search
    private String searchString;

    @Override
    public String toString() {
        return "TeamReqDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", foundedDate='" + foundedDate + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", nameFilter='" + nameFilter + '\'' +
                ", locationFilter='" + locationFilter + '\'' +
                ", foundedDateFilter='" + foundedDateFilter + '\'' +
                ", searchString='" + searchString + '\'' +
                '}';
    }
}
