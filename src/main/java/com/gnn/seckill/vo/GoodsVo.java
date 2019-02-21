package com.gnn.seckill.vo;

import com.gnn.seckill.model.Goods;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
	private Double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
}
