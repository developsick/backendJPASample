package com.ams.backendjpasample.dto.response;

import lombok.Getter;

@Getter
public class AmsResDto {

//    @ApiModelProperty(value = "msg", example = "msg")
    private String msg;

//    @ApiModelProperty(value = "code", example = "code값")
    private Long code;


    public AmsResDto(String msg, Long code) {
        this.msg = msg;
        this.code = code;
    }

    public AmsResDto() {
        this.msg = "";
    }

    @Override
    public String toString() {
        return "SampleReqDto{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
