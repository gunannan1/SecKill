package com.gnn.seckill.redis;

/**
 * 订单相关的key
 */
public class OrderKey extends BasePrefix {

    public OrderKey( String prefix) {
        super( prefix);
    }


    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
