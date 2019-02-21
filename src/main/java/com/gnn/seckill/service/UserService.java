package com.gnn.seckill.service;


import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     *
     * @Description 用户注册
     */
    boolean register(HttpServletResponse response , String userName , String passWord , String salt);
}
