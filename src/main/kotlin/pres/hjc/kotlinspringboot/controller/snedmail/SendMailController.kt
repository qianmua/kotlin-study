package pres.hjc.kotlinspringboot.controller.snedmail

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMailMessage
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import pres.hjc.kotlinspringboot.function.mail.MailApi
import pres.hjc.kotlinspringboot.mapping.MailInfoMapping
import pres.hjc.kotlinspringboot.service.impl.SendMailServiceImpl
import java.awt.TextArea
import java.io.File
import javax.annotation.Resource
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeUtility

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/18
@time 1:08
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/mail")
class SendMailController{

    @Autowired
    private lateinit var mailApi:MailApi

    @Autowired
    private lateinit var sendMailService:SendMailServiceImpl

    @Autowired
    private lateinit var mailMapping:MailInfoMapping

    private val logger by lazy { LoggerFactory.getLogger(SendMailController::class.java) }

    @PostMapping("send/group")
    @ResponseBody
    fun sendGroup(@RequestParam(value = "id", required = true)group:String,
                  @RequestParam(value = "title" ,required = true)title:String ,
                  @RequestParam(value = "title2" ,required = true)title2:String ,
                  @RequestParam(value = "txt" , required = true)txt:String ):String{
        println("how is txt????")
        mailApi.sendMail(group,title,title2)
        return "success"
    }
    @PostMapping("send")
    @ResponseBody
    fun sendmailHtml():String{

        return "SUCCESS"
    }
}
