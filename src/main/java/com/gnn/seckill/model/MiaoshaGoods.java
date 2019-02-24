package com.gnn.seckill.model;

import lombok.*;

import java.util.Date;

/**
 * 专供秒杀的商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaGoods {
	private Long id;
	private Long goodsId;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
}
