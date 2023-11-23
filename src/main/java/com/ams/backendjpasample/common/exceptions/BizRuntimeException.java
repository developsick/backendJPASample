package com.ams.backendjpasample.common.exceptions;

import com.ams.backendjpasample.common.enums.CommonError;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BizRuntimeException extends RuntimeException {
    private CommonError commonErr;
    private String detailMsg;

    public BizRuntimeException(String detailMsg){
        super(detailMsg);
        this.detailMsg = detailMsg;
    }

    public BizRuntimeException(CommonError enCommonErr){
        super(enCommonErr.getMsg());
        this.commonErr = enCommonErr;
        this.detailMsg = enCommonErr.getMsg();
    }

    public BizRuntimeException(CommonError enCommonErr, String detailMsg){
        super(detailMsg);
        this.commonErr = enCommonErr;
        this.detailMsg = detailMsg;
    }

    public BizRuntimeException(CommonError enCommonErr, Exception cause){
        super(cause);
        this.commonErr = enCommonErr;
        this.detailMsg = cause.getLocalizedMessage();
    }

    public BizRuntimeException(CommonError enCommonErr, Exception cause, String detailMsg){
        super(cause);
        this.commonErr = enCommonErr;
        this.detailMsg = detailMsg;
    }

}
