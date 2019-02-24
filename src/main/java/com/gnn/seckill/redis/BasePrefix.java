package com.gnn.seckill.redis;


/**
 * 基本的key前缀
 */
public abstract class BasePrefix implements  KeyPrefix {

    private int expireSeconds;

    private String prefix ;

    public BasePrefix(int expireSeconds ,  String prefix ){

        this.expireSeconds = expireSeconds ;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
       this(0,prefix);
    }

    @Override
    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    /**
     * 可确定获取唯一key,通过 : 隔开可以在redis创建子目录
     * @return
     */
    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className+":" +prefix;
    }
}
