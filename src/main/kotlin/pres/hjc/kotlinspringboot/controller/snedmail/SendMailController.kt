package pres.hjc.kotlinspringboot.controller.snedmail

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/18
@time 1:08
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/send")
class SendMailController(@Autowired private val javaMailSenderImpl: JavaMailSenderImpl){


    @RequestMapping("t")
    @ResponseBody
    fun sendMail1():String{
        val simpleMailMessager = SimpleMailMessage()
        simpleMailMessager.setSubject("kotlin test2!")
        simpleMailMessager.setText("this is a kotlin test page . thank you!")
        simpleMailMessager.setTo("2674521520@qq.com")
        simpleMailMessager.setFrom("2174521520@qq.com")
        javaMailSenderImpl.send(simpleMailMessager)
        return "send message."
    }
}


        /*MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("测试标题1");
        helper.setText("<h1>测试内容1</h1>" , true);
        helper.setTo("");
        helper.setFrom("");
        *//*文件*//*
        helper.addAttachment("1.jpg", new File("F:\\ACG_pictures_Main\\bizhi\\0d289ed2ff9c1080a5e929770252957a.jpg"));
        mailSender.send(mimeMessage);*/
