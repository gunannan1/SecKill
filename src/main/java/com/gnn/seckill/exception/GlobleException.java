package com.gnn.seckill.exception;


import com.gnn.seckill.enums.ResultEnum;

public class GlobleException extends RuntimeException {


    private ResultEnum status;

    public GlobleException(ResultEnum status){
        super("code: "+status.getCode()+", msg: "+status.getMessage());
        this.status = status;
    }

    public ResultEnum getStatus() {
        return status;
    }

    public void setStatus(ResultEnum status) {
        this.status = status;
    }
}
