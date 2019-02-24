package com.gnn.seckill.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 消息中心主体表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoShaMessageInfo implements Serializable {

    //id
    private Integer id ;

    //消息分布式id
    private Long messageId ;

    //消息内容
    private String content ;

    //创建时间
    private Date createTime;

    //消息状态，0有效，1失效
    private Integer status ;

    //结束时间
    private Date overTime ;

    //消息类型，0 秒杀消息 1 购买消息 2 系统消息
    private Integer messageType ;

    //发送类型，0 pc 1 android 2 ios
    private Integer sendType ;

    //商品名称
    private String goodName ;

    //价格
    private BigDecimal price ;


}
