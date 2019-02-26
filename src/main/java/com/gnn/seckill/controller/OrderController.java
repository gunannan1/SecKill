package com.gnn.seckill.controller;

import com.gnn.seckill.common.Result;
import com.gnn.seckill.model.MiaoshaUser;
import com.gnn.seckill.model.OrderInfo;
import com.gnn.seckill.redis.RedisService;
import com.gnn.seckill.service.GoodsService;
import com.gnn.seckill.service.MiaoShaUserService;
import com.gnn.seckill.service.OrderService;
import com.gnn.seckill.vo.GoodsVo;
import com.gnn.seckill.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.gnn.seckill.enums.ResultStatus.ORDER_NOT_EXIST;
import static com.gnn.seckill.enums.ResultStatus.SESSION_ERROR;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoShaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;


	/**
	 * 获得订单信息
	 * @param model
	 * @param user
	 * @param orderId
	 * @return
	 */
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser user, @RequestParam("orderId") long orderId) {

		Result<OrderDetailVo> result = Result.build();
		//用户不存在就报错
		if (user == null) {
			result.withError(SESSION_ERROR.getCode(), SESSION_ERROR.getMessage());
			return result;
		}
		//订单不存在就报错
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
			result.withError(ORDER_NOT_EXIST.getCode(), ORDER_NOT_EXIST.getMessage());
			return result;
    	}

    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	result.setData(vo);
    	return result;
    }
    
}
