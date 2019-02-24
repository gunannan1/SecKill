package com.gnn.seckill.vo;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoShaMessageVo implements Serializable {


    private static final long serialVersionUID = 7793625661747223332L;
    private Integer id ;

    private Long userId;

    private String goodId ;

    private Date orderId;

    private Long messageId ;

    private String content ;

    private Date createTime;

    private Integer status ;

    private Date overTime ;

    private Integer messageType ;

    private Integer sendType ;

    private String goodName ;

    private BigDecimal price ;

}
