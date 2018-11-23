package com.lxcun.mail.controller;

import com.lxcun.mail.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {
    @Autowired
    private MailService mailService;
    @RequestMapping(value="sendMail",method= RequestMethod.GET)
    @ResponseBody
    public String sendMail(){
        if(mailService.sendEmail("654679423@qq.com","java邮箱测试","测试发送"))
         return "success";
        else
            return "false";
    }
}
