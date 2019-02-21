package com.gnn.seckill.model;

import lombok.*;

@Data
@AllArgsConstructor //生成一个无参数的构造方法
@NoArgsConstructor //生成一个包含所有参数的构造方法
public class Goods {
	private Long id;
	private String goodsName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;
}
