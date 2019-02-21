package com.gnn.seckill.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor //生成一个无参数的构造方法
@NoArgsConstructor //生成一个包含所有参数的构造方法
public class MiaoshaGoods {
	private Long id;
	private Long goodsId;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;

}
