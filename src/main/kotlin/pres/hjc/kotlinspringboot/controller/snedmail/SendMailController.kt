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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.mapping.MailInfoMapping
import java.io.File
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
    private lateinit var javaMailSenderImpl: JavaMailSenderImpl

    @Autowired
    private lateinit var mailMapping:MailInfoMapping

    private val logger by lazy { LoggerFactory.getLogger(SendMailController::class.java) }


    @PostMapping("send")
    @ResponseBody
    fun sendmailHtml():String{
        try {
            val mimeMessage:MimeMessage = javaMailSenderImpl.createMimeMessage()
            val mailBody = MimeMessageHelper(mimeMessage,true)
            val mailName= MimeUtility.encodeText("咕咕鸟")
            /*body*/
            mailBody.setFrom(InternetAddress("2174521520@qq.com",mailName))
            mailBody.setTo("2674521520@qq.com")
            mailBody.setSubject("邮件模板测试_1")
            mailBody.setText("<h1>邮件模板测试,</h1>",true)
            //send image file
            mailBody.addAttachment("1.jpg", File("F:\\ACG_pictures_Main\\bizhi\\0d289ed2ff9c1080a5e929770252957a.jpg"));
            //路径资源文件
            /*try {
                val file2 = FileSystemResource(File("src/resources/static/html/img/bg.jpg"))
                mailBody.addInline("pictures2",file2)

            } catch (e: Exception) {
                logger.error("file path error : ${e.message}")
            }*/
            javaMailSenderImpl.send(mimeMessage)
        } catch (e: Exception) {
            logger.error("fail: ${e.message}")
        }
        return "send mail ok!"
    }
}
