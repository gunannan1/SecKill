package com.gnn.seckill.vo;

import com.gnn.seckill.model.Order;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVo {
	private GoodsVo goods;
	private Order order;
	public GoodsVo getGoods() {
		return goods;
	}
	public void setGoods(GoodsVo goods) {
		this.goods = goods;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
