package com.gnn.seckill.dao;

import com.gnn.seckill.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user where username = #{username}")
    public User getByNickname(@Param("username") String nickname ) ;

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") long id ) ;

    @Update("update user set password = #{password} where id = #{id}")
    public void updatePassword(User user);


    @Insert("insert into user (id , username ,phone,password ,email, salt ,head,register_date,last_login_date," +
            "login_count) value (#{id},#{username},#{phone},#{password},#{email},#{salt},#{head},#{registerDate}," +
            "#{lastLoginDate},#{loginCount}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public void addUser(User user);


}
