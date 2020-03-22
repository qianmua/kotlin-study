package pres.hjc.kotlinspringboot.config.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 18:22
@version 1.0
To change this template use File | Settings | File Templates.
 */
@EnableWebSecurity
@Configuration
class MySecurityConfig : WebSecurityConfigurerAdapter() {

//    @Autowired
//    private lateinit var hrService:HrService


    /**
     * main
     */
    override fun configure(http: HttpSecurity?) {
//        super.configure(http)

    }

    /**
     * 配置密码加密格式
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
//        super.configure(auth)
    }

    /**
     * 配置资源拦截
     */
    override fun configure(web: WebSecurity?) {
//        super.configure(web)
    }



}