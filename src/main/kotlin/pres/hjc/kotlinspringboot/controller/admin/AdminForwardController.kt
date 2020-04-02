package pres.hjc.kotlinspringboot.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import pres.hjc.kotlinspringboot.tools.ConstantUtils
import pres.hjc.kotlinspringboot.tools.CookieUtils
import javax.servlet.http.HttpServletRequest

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 16:34
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("admin")
class AdminForwardController {

    companion object {
        private const val suf = ConstantUtils.suffix
    }
    @GetMapping("/")
    fun adminIndex(request: HttpServletRequest):String {
        val sessionToken = CookieUtils.getCookie(request,"SESSION_TOKEN")
        return if (sessionToken == null) "forward:/admin/login.html" else "admin/index"
    }
    @GetMapping("/index$suf")
    fun board(): String = "admin/index"

    @GetMapping("login$suf")
    fun login():String = "admin/login"


}