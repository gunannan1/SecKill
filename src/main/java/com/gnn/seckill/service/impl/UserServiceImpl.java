package com.gnn.seckill.service.impl;

import com.gnn.seckill.dao.UserDao;
import com.gnn.seckill.model.User;
import com.gnn.seckill.service.UserService;
import com.gnn.seckill.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    public static final String COOKI_NAME_TOKEN = "token";
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    UserDao userDao;



    @Override
    public boolean register(HttpServletResponse response, String username, String password, String salt) {
        User user=new User();
        user.setUsername(username);
        String DBPassWord =  MD5Util.formPassToDBPass(password , salt);
        user.setPassword(DBPassWord);
        user.setRegisterDate(new Date());
        user.setSalt(salt);
//        user.setEmail("");
        try {

        }catch (Exception e){
            logger.error("注册失败");
        }








        return false;
    }
}
