package pres.hjc.kotlinspringboot.intercaptor.security.handler

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 19:06
@version 1.0
To change this template use File | Settings | File Templates.
 */

/**
 * @Author: Galen
 * @Date: 2019/3/28-9:17
 * @Description: 认证失败的处理
 **/

class FailureHandler:AuthenticationFailureHandler{
    override fun onAuthenticationFailure(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: AuthenticationException?) {
        val error:String?
        if (p2 is BadCredentialsException || p2 is UsernameNotFoundException)
            error = "账户名或者密码错误"
        else
            error = "登录失败"
        p1!!.status = 401
    }
}

