package com.gnn.seckill.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor //生成一个无参数的构造方法
@NoArgsConstructor //生成一个包含所有参数的构造方法
public class Order {
	private Long id;
	private Long userId;
	private Long goodsId;
	private Long  deliveryAddrId;
	private String goodsName;
	private Integer goodsCount;
	private Double goodsPrice;
	private Integer orderChannel;
	private Integer status;
	private Date createDate;
	private Date payDate;
}
