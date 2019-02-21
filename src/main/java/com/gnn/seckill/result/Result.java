package com.gnn.seckill.result;

import com.gnn.seckill.enums.ResultEnum;

import java.io.Serializable;

public class Result<T> extends AbstractResult implements Serializable {
    private static final long serialVersionUID = 867933019328199779L;
    private T data;
    private Integer count;

    protected Result(ResultEnum status, String message) {
        super(status, message);
    }
    protected Result(ResultEnum status) {
        super(status);
    }
    public static <T> Result<T> build() {
        return new Result(ResultEnum.SUCCESS, (String)null);
    }

    public static <T> Result<T> build(String message) {
        return new Result(ResultEnum.SUCCESS, message);
    }

    public static <T> Result<T> error(ResultEnum status) {
        return new Result<T>(status);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void success(T value) {
        this.success();
        this.data = value;
        this.count = 0;
    }

}
