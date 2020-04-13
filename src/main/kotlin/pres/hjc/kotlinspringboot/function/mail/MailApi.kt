package pres.hjc.kotlinspringboot.function.mail

import org.slf4j.LoggerFactory
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import pres.hjc.kotlinspringboot.typeset.DayGetCont
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeUtility

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/13
@time 7:27
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Component
open class MailApi {

    private val javaMailSenderImpl by lazy { JavaMailSenderImpl() }
    private val logger by lazy { LoggerFactory.getLogger(MailApi::class.java) }

    private val mailName = "咕咕鸟"
    private val address = "2174521520@qq.com"

    open fun sendMail(group:String,title1:String,title2: String){
        try {
            val mimeMessage: MimeMessage = javaMailSenderImpl.createMimeMessage()
            val mailBody = MimeMessageHelper(mimeMessage,true)
            val mailName= MimeUtility.encodeText(this.mailName) ?: address
            mailBody.setFrom(InternetAddress(address,mailName))
            /*body*/
            mailBody.setTo(group)
            mailBody.setSubject(title1)
            mailBody.setText(viewsText(title2),true)

            javaMailSenderImpl.send(mimeMessage)
        } catch (e: Exception) {
            logger.error("fail: ${e.message}")
        }
    }

    open fun viewsText(title2:String):String = """
        <table border="0">
            <caption>$title2</caption>
            <tr>
                <th>${DayGetCont.MON}</th>
                <th>${DayGetCont.TUE}</th>
                <th>${DayGetCont.WED}</th>
                <th>${DayGetCont.THU}</th>
                <th>${DayGetCont.FRI}</th>
                <th>${DayGetCont.SAT}</th>
                <th>${DayGetCont.SUN}</th>
            </tr>
            <tr><td>测试用</td><td>测试用</td><td>测试用</td><td>测试用</td><td>测试用</td><td>测试用</td><td>测试用</td></tr>
        </table>
    """.trimIndent()

    //send image file
    //mailBody.addAttachment("1.jpg", File("F:\\ACG_pictures_Main\\bizhi\\0d289ed2ff9c1080a5e929770252957a.jpg"));
    //路径资源文件
    /*try {
        val file2 = FileSystemResource(File("src/resources/static/html/img/bg.jpg"))
        mailBody.addInline("pictures2",file2)

    } catch (e: Exception) {
        logger.error("file path error : ${e.message}")
    }*/
}