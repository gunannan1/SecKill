package com.gnn.seckill.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息中心用戶存储关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoShaMessageUser implements Serializable {

    private Long id ;

    private Long userId ;

    private Long messageId ;

    private String goodId ;

    private Date orderId;

}
