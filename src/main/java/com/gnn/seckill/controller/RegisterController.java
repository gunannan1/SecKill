package com.gnn.seckill.controller;


import com.gnn.seckill.common.Result;
import com.gnn.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


import static com.gnn.seckill.enums.ResultStatus.*;


@Controller
public class RegisterController {


    @Autowired
    private UserService userService;



    @RequestMapping("/register")
    public String registerIndex(){
        return "register";
    }

    /**
     * 注册网站
     * @param userName
     * @param passWord
     * @param salt
     * @return
     */
    @RequestMapping("/do_register")
    @ResponseBody
    public Result<String> register(@RequestParam("username") String userName ,
                                   @RequestParam("password") String passWord,
                                   @RequestParam("salt") String salt, HttpServletResponse response ){

        Result<String> result = Result.build();
        boolean registerInfo  = userService.register(response,userName,passWord,salt);

        if(!registerInfo){
           result.withError(RESIGETER_FAIL.getCode(),RESIGETER_FAIL.getMessage());
           return result;
        }
        return result;
    }

}
