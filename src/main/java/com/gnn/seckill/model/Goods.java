package com.gnn.seckill.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	private Long id;
	private String goodsName;
	private String goodsTitle;
	private String goodsImg;
	private String goodsDetail;
	private Double goodsPrice;
	private Integer goodsStock;
}
