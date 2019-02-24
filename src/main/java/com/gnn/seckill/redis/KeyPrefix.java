package com.gnn.seckill.redis;

/**
 * 定义key前缀
 */
public interface KeyPrefix {

    public int expireSeconds() ;

    public String getPrefix() ;

}
