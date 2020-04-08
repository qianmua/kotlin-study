package pres.hjc.kotlinspringboot.config.snedmail

import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Component

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/7
@time 14:18
@version 1.0
To change this template use File | Settings | File Templates.
 */
//@Configuration
@Component
class SendMailConfig {
//    @Bean
    fun javaMailSenderImpl(): JavaMailSenderImpl {
        return JavaMailSenderImpl() // error
    }
}