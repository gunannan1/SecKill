package com.gnn.seckill.service;


import com.gnn.seckill.dao.OrderDao;
import com.gnn.seckill.model.MiaoshaOrder;
import com.gnn.seckill.model.MiaoshaUser;
import com.gnn.seckill.model.OrderInfo;
import com.gnn.seckill.redis.RedisService;
import com.gnn.seckill.redis.prefix.OrderKey;
import com.gnn.seckill.utils.DateTimeUtil;
import com.gnn.seckill.vo.GoodsVo;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;

	@Autowired
	private RedisService redisService ;
	
	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
		return	redisService.get(OrderKey.getMiaoshaOrderByUidGid,""+userId+"_"+goodsId,MiaoshaOrder.class) ;
	}

	public OrderInfo getOrderById(long orderId) {
		return orderDao.getOrderById(orderId);
	}

	@Transactional
	public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {

		//创建订单详情
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(Long.valueOf(user.getNickname()));
		orderDao.insert(orderInfo);

		//创建秒杀订单
		MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
		miaoshaOrder.setGoodsId(goods.getId());
		miaoshaOrder.setOrderId(orderInfo.getId());
		miaoshaOrder.setUserId(Long.valueOf(user.getNickname()));
		orderDao.insertMiaoshaOrder(miaoshaOrder);
		redisService.set(OrderKey.getMiaoshaOrderByUidGid,""+user.getNickname()+"_"+goods.getId(),miaoshaOrder) ;
		return orderInfo;
	}

	/**
	 * 关闭订单
	 * @param hour
	 */
	public void closeOrder(int hour){
		//订单关闭时间设为当前时间减去相应的小时
		Date closeDateTime = DateUtils.addHours(new Date(),-hour);
		//如果这个订单关闭时间等于订单创建时间，就关闭订单
		List<OrderInfo> orderInfoList = orderDao.selectOrderStatusByCreateTime(0, DateTimeUtil.dateToStr(closeDateTime));
		for (OrderInfo orderInfo:orderInfoList){
			System.out.println("orderinfo  infomation "+orderInfo.getGoodsName());
		}
	}

	
}
