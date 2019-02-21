package com.gnn.seckill.result;


import com.gnn.seckill.enums.ResultEnum;

public class AbstractResult {
    private ResultEnum status;
    private int code;
    private String message;

    protected AbstractResult(ResultEnum status, String message) {
        this.code = status.getCode();
        this.status = status;
        this.message = message;
    }

    protected AbstractResult(ResultEnum status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.status = status;
    }

    public static boolean isSuccess(AbstractResult result) {
        return result != null && result.status == ResultEnum.SUCCESS && result.getCode() == ResultEnum.SUCCESS.getCode();
    }

    public AbstractResult withError(ResultEnum status) {
        this.status = status;
        return this;
    }

    public AbstractResult withError(String message) {
        this.status = ResultEnum.SYSTEM_ERROR;
        this.message = message;
        return this;
    }

    public AbstractResult withError(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public AbstractResult success() {
        this.status = ResultEnum.SUCCESS;
        return this;
    }
    public ResultEnum getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message == null ? this.status.getMessage() : this.message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
