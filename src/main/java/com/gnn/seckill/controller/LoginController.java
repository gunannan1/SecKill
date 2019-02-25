package com.gnn.seckill.controller;


import com.gnn.seckill.common.Result;
import com.gnn.seckill.service.MiaoShaUserService;
import com.gnn.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoShaUserService userService;

    @RequestMapping("/login")
    public String tologin(LoginVo loginVo, Model model) {
        logger.info(loginVo.toString());

        //TODO  lua记录登录次数
//        RedisLua.vistorCount(COUNTLOGIN);
//        String count = RedisLua.getVistorCount(COUNTLOGIN).toString();
//        logger.info("访问网站的次数为:{}",count);
//        model.addAttribute("count",count);
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> dologin(HttpServletResponse response, @Valid LoginVo loginVo) {
        Result<Boolean> result = Result.build();
        logger.info(loginVo.toString());
        userService.login(response, loginVo);
        return result;
    }


    @RequestMapping("/create_token")
    @ResponseBody
    public String createToken(HttpServletResponse response, @Valid LoginVo loginVo) {
        logger.info(loginVo.toString());
        String token = userService.createToken(response, loginVo);
        return token;
    }
}
