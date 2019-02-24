package com.gnn.seckill.exception;


import com.gnn.seckill.common.enums.ResultStatus;

/**
 * 定义全局异常
 */
public class GlobleException extends RuntimeException {


    private ResultStatus status;

    public GlobleException(ResultStatus status){
        super("code: "+status.getCode()+", msg: "+status.getMessage());
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }

    public void setStatus(ResultStatus status) {
        this.status = status;
    }
}
