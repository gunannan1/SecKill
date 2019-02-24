package com.gnn.seckill.model;

import lombok.*;

/**
 * 秒杀订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaOrder {
	private Long id;
	private Long userId;
	private Long  orderId;
	private Long goodsId;
}
