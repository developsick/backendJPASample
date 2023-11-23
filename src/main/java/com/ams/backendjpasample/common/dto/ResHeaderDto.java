package com.ams.backendjpasample.common.dto;

import com.ams.backendjpasample.common.enums.EnumError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@ApiModel(value = "ResHeaderDto", description = "API Response Header")
public class ResHeaderDto {

//    @ApiModelProperty(value = "결과 코드", example = "CS0001", required = true, position = 0)
    private String resultCode;
//    @ApiModelProperty(value = "결과 메시지", example = "Success", required = true, position = 1)
    private String resultMsg;

    public ResHeaderDto(EnumError error ) {
        this.resultCode = error.getCode();
        this.resultMsg = error.getMsg();
    }

    public ResHeaderDto(EnumError error, String resultMsg ) {
        this.resultCode = error.getCode();
        this.resultMsg = resultMsg;
    }

    public static ResHeaderDto of( EnumError error ) {
        return new ResHeaderDto( error );
    }
}
