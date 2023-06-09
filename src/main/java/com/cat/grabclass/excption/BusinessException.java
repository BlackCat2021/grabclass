package com.cat.grabclass.excption;

import com.cat.grabclass.common.contants.ResultStatus;

/**
 * @author Mr.xin
 */
public class BusinessException extends Exception {


    Integer code;
    public BusinessException(Integer code,String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(ResultStatus status) {
        super(status.message);
        this.code = status.code;
    }


    public Integer getCode(){
        return this.code;
    }
}
