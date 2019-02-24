package com.gnn.seckill.service;


import com.gnn.seckill.common.SnowflakeIdWorker;
import com.gnn.seckill.enums.MessageStatus;
import com.gnn.seckill.dao.UserDao;
import com.gnn.seckill.exception.GlobleException;
import com.gnn.seckill.model.User;
import com.gnn.seckill.rabbitmq.MQSender;
import com.gnn.seckill.redis.RedisService;
import com.gnn.seckill.redis.UserKey;
import com.gnn.seckill.utils.MD5Util;
import com.gnn.seckill.utils.UUIDUtil;
import com.gnn.seckill.vo.LoginVo;
import com.gnn.seckill.vo.MiaoShaMessageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static com.gnn.seckill.enums.ResultStatus.*;


@Service
public class UserService {

    public static final String COOKIE_NAME_TOKEN = "token" ;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService ;

    @Autowired
    private MQSender sender ;


    /**
     * 通过token得到用户
     * @param response
     * @param token
     * @return
     */
    public User getByToken(HttpServletResponse response , String token) {

        if(StringUtils.isEmpty(token)){
            return null ;
        }
        User user =redisService.get(UserKey.token,token, User.class) ;
        if(user!=null) {
            addCookie(response, token, user);
        }
        return user ;

    }

    /**
     * 通过用户名得到用户
     * @param nickName
     * @return
     */
    public User getUserName(String nickName) {
        //取缓存
        User user = redisService.get(UserKey.getByUserName, ""+nickName, User.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = userDao.getByUsername(nickName);
        if(user != null) {
            redisService.set(UserKey.getByUserName, ""+nickName, user);
        }
        return user;
    }


    /**
     * 更新密码
     * @param token
     * @param nickName
     * @param formPass
     * @return
     */
    // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    public boolean updatePassword(String token, String nickName, String formPass) {
        //取user
        User user = getUserName(nickName);
        if(user == null) {
            throw new GlobleException(MOBILE_NOT_EXIST);
        }
        //更新数据库
        User toBeUpdate = new User();
        toBeUpdate.setUsername(nickName);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        userDao.updatePassword(toBeUpdate);
        //处理缓存
        redisService.delete(UserKey.getByUserName, ""+nickName);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(UserKey.token, token, user);
        return true;
    }


    /**
     * 用户注册
     * @param response
     * @param userName
     * @param passWord
     * @param salt
     * @return
     */
    public boolean register(HttpServletResponse response , String userName , String passWord , String salt) {
        //创建用户
        User miaoShaUser =  new User();
        miaoShaUser.setUsername(userName);
        String DBPassWord =  MD5Util.formPassToDBPass(passWord , salt);
        miaoShaUser.setPassword(DBPassWord);
        miaoShaUser.setRegisterDate(new Date());
        miaoShaUser.setSalt(salt);


        try {
            userDao.addUser(miaoShaUser);
            User user = userDao.getByUsername(miaoShaUser.getUsername());
            if(user == null){
                return false;
            }

            //创建消息vo
            MiaoShaMessageVo vo = new MiaoShaMessageVo();
            vo.setContent(MessageStatus.ContentEnum.system_message_register.getMessage());
            vo.setCreateTime(new Date());
            vo.setMessageId(SnowflakeIdWorker.getOrderId(0,0));
            vo.setSendType(0);
            vo.setStatus(0);
            vo.setMessageType(MessageStatus.messageType.system_message.getType());
            vo.setUserId(miaoShaUser.getId());
            sender.sendRegisterMessage(vo);


            //生成cookie 将session返回游览器 分布式session
            String token= UUIDUtil.uuid();
            addCookie(response, token, user);
        } catch (Exception e) {
            logger.error("注册失败",e);
            return false;
        }
        return true;
    }

    /**
     * 用户登录
     * @param response
     * @param loginVo
     * @return
     */
    public boolean login(HttpServletResponse response , LoginVo loginVo) {
        if(loginVo ==null){
            throw  new GlobleException(SYSTEM_ERROR);
        }

        String mobile =loginVo.getMobile();
        String password =loginVo.getPassword();
        User user = getUserName(mobile);
        if(user == null) {
            throw new GlobleException(MOBILE_NOT_EXIST);
        }

        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(password,saltDb);
        if(!calcPass.equals(dbPass)){
            throw new GlobleException(PASSWORD_ERROR);
        }
        //生成cookie 将session返回游览器 分布式session
        String token= UUIDUtil.uuid();
        addCookie(response, token, user);
        return true ;
    }


    /**
     * 创建token
     * @param response
     * @param loginVo
     * @return
     */
    public String createToken(HttpServletResponse response , LoginVo loginVo) {
        if(loginVo ==null){
            throw  new GlobleException(SYSTEM_ERROR);
        }

        String mobile =loginVo.getMobile();
        String password =loginVo.getPassword();
        User user = getUserName(mobile);
        if(user == null) {
            throw new GlobleException(MOBILE_NOT_EXIST);
        }

        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(password,saltDb);
        if(!calcPass.equals(dbPass)){
            throw new GlobleException(PASSWORD_ERROR);
        }
        //生成cookie 将session返回游览器 分布式session
        String token= UUIDUtil.uuid();
        addCookie(response, token, user);
        return token ;
    }

    /**
     * 把token写入cookie
     * @param response
     * @param token
     * @param user
     */
    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        //设置有效期
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }



}
