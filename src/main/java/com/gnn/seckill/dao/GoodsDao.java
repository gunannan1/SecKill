package com.gnn.seckill.dao;


import com.gnn.seckill.model.MiaoshaGoods;
import com.gnn.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsDao {
	
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();

	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

	@Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
	public int reduceStock(MiaoshaGoods g);

	@Update("update miaosha_goods set stock_count = #{stockCount},version=0 where goods_id = #{goodsId}")
	public int resetStock(MiaoshaGoods g);

	//乐观锁
	@Update("update miaosha_goods set stock_count = stock_count - 1,version= version + 1 where goods_id = #{goodsId} and stock_count > 0 and version = #{version}")
	public int reduceStock2(MiaoshaGoods g);


}
