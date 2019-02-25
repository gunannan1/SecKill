package com.gnn.seckill.redis.prefix;

/**
 * 用户 前缀
 */
public class MiaoShaUserKey extends  BasePrefix{
    public static final int TOKEN_EXPIRE = 3600 *24*2; //过期时间2天
    public static MiaoShaUserKey token = new MiaoShaUserKey(TOKEN_EXPIRE,"tk") ;
    public static MiaoShaUserKey getByNickName = new MiaoShaUserKey(0, "nickname");

    public MiaoShaUserKey(int expireSeconds ,String prefix) {
        super(expireSeconds,prefix);
    }
}
