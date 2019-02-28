package com.gnn.seckill.rabbitmq;


import com.gnn.seckill.model.MiaoshaOrder;
import com.gnn.seckill.model.MiaoshaUser;
import com.gnn.seckill.redis.RedisService;
import com.gnn.seckill.service.GoodsService;
import com.gnn.seckill.service.MiaoShaMessageService;
import com.gnn.seckill.service.MiaoshaService;
import com.gnn.seckill.service.OrderService;
import com.gnn.seckill.vo.GoodsVo;
import com.gnn.seckill.vo.MiaoShaMessageVo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MQReceiver {

		private static Logger log = LoggerFactory.getLogger(MQReceiver.class);
		
		@Autowired
		RedisService redisService;
		
		@Autowired
		GoodsService goodsService;

		@Autowired
		OrderService orderService;
		
		@Autowired
		MiaoshaService miaoshaService;

		@Autowired
		MiaoShaMessageService messageService ;
		
		@RabbitListener(queuesToDeclare =@Queue(MQConfig.MIAOSHA_QUEUE))
		public void receive(String message) {
			log.info("receive message:"+message);
			MiaoshaMessage mm  = RedisService.stringToBean(message, MiaoshaMessage.class);
			MiaoshaUser user = mm.getUser();
			long goodsId = mm.getGoodsId();

			GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
	    	int stock = goods.getStockCount();
	    	if(stock <= 0) {
	    		return;
	    	}
	    	//判断是否已经秒杀到了
	    	MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(Long.valueOf(user.getNickname()), goodsId);
	    	if(order != null) {
	    		return;
	    	}
	    	//减库存 下订单 写入秒杀订单
	    	miaoshaService.miaosha(user, goods);
		}



	@RabbitListener(queuesToDeclare =@Queue(MQConfig.MIAOSHATEST))
	public void receiveMiaoShaMessage(Message message, Channel channel) throws IOException {
		log.info("接受到的消息为:{}",message);
		String messRegister = new String(message.getBody(), "UTF-8");
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
		MiaoShaMessageVo msm  = RedisService.stringToBean(messRegister, MiaoShaMessageVo.class);
		messageService.insertMs(msm);
		}
}
