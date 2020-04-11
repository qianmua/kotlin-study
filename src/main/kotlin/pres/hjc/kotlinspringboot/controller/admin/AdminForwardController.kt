package pres.hjc.kotlinspringboot.controller.admin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.target.Logs
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl
import pres.hjc.kotlinspringboot.tools.ConstantUtils
import pres.hjc.kotlinspringboot.tools.CookieUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 16:34
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/admin")
class AdminForwardController {

    companion object {
        private const val suf = ConstantUtils.suffix
    }

    @Autowired
    private lateinit var userServiceImpl: UserServiceImpl

    @Logs("index")
    @GetMapping("", "index","index$suf")
    fun adminIndex(request: HttpServletRequest):String {
        val sessionToken = CookieUtils.getCookie(request,"SESSION_TOKEN")
        if (sessionToken == null) {
            return "redirect:/admin/login.html"
        } else {
            return "admin/index"
        }
    }

    /*@GetMapping("/index$suf")
    fun board(request: HttpServletRequest): String {
        val sessionToken = CookieUtils.getCookie(request,"SESSION_TOKEN")
        return if (sessionToken == null) "redirect:/admin/login.html" else "admin/index"
    }*/
    @Logs("pagesLogin")
    @GetMapping("login$suf")
    fun loginPage():String = "admin/login"

    /**
     * ajax login
     */
    @Logs("login")
    @PostMapping("login")
    @ResponseBody
    fun login(name:String,
              password:String,
              request: HttpServletRequest,
              response:HttpServletResponse,
              model:Model):String{
        if (name.length and password.length < 1 ){
            return "error"
        }else{
            val userModel = userServiceImpl.queryAdminId(name, password, request, response) ?: return "fail"
            request.session.setAttribute("user",userModel)
        }
        return "success"
    }
}