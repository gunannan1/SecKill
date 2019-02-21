package com.gnn.seckill.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 消息中心主体表
 */
@Data
@AllArgsConstructor //生成一个无参数的构造方法
@NoArgsConstructor //生成一个包含所有参数的构造方法
public class MiaoShaMessageInfo implements Serializable {

    private Integer id ;

    private Long messageId ;

    private Long userId ;

    private String content ;

    private Date createTime;

    private Integer status ;

    private Date overTime ;

    private Integer messageType ;

    private Integer sendType ;

    private String goodName ;

    private BigDecimal price ;

    private String messageHead ;

}
