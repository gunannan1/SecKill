package com.gnn.seckill.vo;

import com.gnn.seckill.model.MiaoshaUser;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailVo {
	private int miaoshaStatus = 0;
	private int remainSeconds = 0;
	private GoodsVo goods ;
	private MiaoshaUser miaoshaUser;

}
