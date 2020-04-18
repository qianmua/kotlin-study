package pres.hjc.kotlinspringboot.controller.admin.rabbitmq

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/18
@time 14:01
@version 1.0
To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/snedrmq")
class SendMessageRabbitController {
    @Autowired
    private lateinit var rabbitTemplate:RabbitTemplate

    @GetMapping("test")
    fun snedDirect():String{
        val msgID = PublicToolsUtils.getUUID()
        val messageData = "test message, hello!"
        val createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val map =HashMap<String,Any>()
        map["messageId"] = msgID
        map["messageData"] = messageData
        map["createTime"] = createTime
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("psaChange", "DirectRouting", map)
        return "ok"
    }
}