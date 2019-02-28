package com.gnn.seckill.controller;


import com.gnn.seckill.common.Result;
import com.gnn.seckill.enums.MessageStatus;
import com.gnn.seckill.enums.ResultStatus;
import com.gnn.seckill.model.MiaoShaMessageInfo;
import com.gnn.seckill.rabbitmq.MQSender;
import com.gnn.seckill.service.MiaoShaMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/message")
public class MiaoShaMessageController {

    @Autowired
    private MiaoShaMessageService messageService;
    @Autowired
    private MQSender sendMessage;

    @RequestMapping(value = "/list", produces = "text/html")
    public String list(@RequestParam(value = "userid",required= true) String userId, Model model) {
        Result resultGeekQ = Result.build();
        if (StringUtils.isBlank(userId)) {
            resultGeekQ.withError(ResultStatus.USER_NOT_EXIST);
        }
        List<MiaoShaMessageInfo> miaoShaMessageInfos = messageService.getmessageUserList(Long.valueOf(userId), null);

        model.addAttribute("message",miaoShaMessageInfos);

        return "message_list";
    }


    @RequestMapping(value = "/getNewMessage", produces = "text/html")
    @ResponseBody
    public String getNewMessage(@RequestParam(value = "userid",required= true) String userId, Model model) {

        if (StringUtils.isBlank(userId)) {
            return "0";
        }
        List<MiaoShaMessageInfo> miaoShaMessageInfos = messageService.getmessageUserList(Long.valueOf(userId), MessageStatus.ZORE);
        if(miaoShaMessageInfos.isEmpty()){
            return "0";
        }else {
            return "1";
        }
    };
}
