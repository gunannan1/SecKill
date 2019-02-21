package com.gnn.seckill.model;

import lombok.*;

@Data
@AllArgsConstructor //生成一个无参数的构造方法
@NoArgsConstructor //生成一个包含所有参数的构造方法
public class MiaoshaOrder {
	private Long id;
	private Long userId;
	private Long  orderId;
	private Long goodsId;
}
