package com.gnn.seckill.dao;


import com.gnn.seckill.model.MiaoShaMessageInfo;
import com.gnn.seckill.model.MiaoShaMessageUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MiaoShaMessageDao {
	
	@Select("select * from miaosha_message where messageid =  #{messageid}  ")
	public List<MiaoShaMessageInfo> listMiaoShaMessage(@Param("messageId") String messageId);

	@Select("<script>select * from miaosha_message_user where 1=1 <if test=\"messageId !=null \">and messageId = #{messageId} </if></script>")
	public List<MiaoShaMessageUser> listMiaoShaMessageUser(@Param("messageId") String messageId);

	@Insert("insert into miaosha_message (id , messageid ,content , create_time ,status,over_time,message_type ,send_type , good_name , price)" +
			"value (#{id},#{messageId},#{content},#{createTime},#{status},#{overTime},#{messageType},#{sendType},#{goodName},#{price}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void insertMiaoShaMessage(MiaoShaMessageInfo miaoShaMessage);

	@Insert("insert into miaosha_message_user (id , userid ,messageid , goodid ,orderid)" +
			"value (#{id},#{userId},#{messageId},#{goodId},#{orderId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void insertMiaoShaMessageUser(MiaoShaMessageUser miaoShaMessageUser);


	@Select(" <script> select * from miaosha_message_user mmu , miaosha_message mm where " +
			" mmu.messageid = mm.messageid and  userid=${userId}  <if test=\"status !=null \">and status = #{status} </if></script> ")
	public List<MiaoShaMessageInfo> listMiaoShaMessageByUserId(@Param("userId") long userId, @Param("status") Integer status);
}
