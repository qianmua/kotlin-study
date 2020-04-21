package pres.hjc.kotlinspringboot.controller.admin

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl
import pres.hjc.kotlinspringboot.target.Logs
import pres.hjc.kotlinspringboot.tools.ConstantUtils
import pres.hjc.kotlinspringboot.tools.CookieUtils
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
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
@RequestMapping("/admins")
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
            return "redirect:/admins/login.html"
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
            //token 放在这里会匹配不上，令牌错误，md5加密冲突
            //封装令牌
            val subject = SecurityUtils.getSubject()
            var password_tolen = password
            password_tolen = PublicToolsUtils.md5Two(password + ConstantUtils.PASSWORD_HEAD)!!
            val usernamePasswordToken = UsernamePasswordToken(name, password_tolen)
            //执行登录
            try {
                subject.login(usernamePasswordToken)
            } catch (e: UnknownAccountException) {
                model.addAttribute("msg","用户名错误")
                return "fail"
            }catch (e: IncorrectCredentialsException){
                model.addAttribute("msg","密码错误")
                return "fail"
            }
            //得到当前请求
            /*val userModel = userServiceImpl.queryAdminId(name, password, request, response) ?: return "fail"
            request.session.setAttribute("user",userModel)*/
        }
        return "success"
    }

    @GetMapping("charts$suf")
    fun charts():String = "admin/charts"

    @GetMapping("form-basic$suf")
    fun formsb():String = "admin/form-basic"

    @GetMapping("form-wizard$suf")
    fun formsw():String = "admin/form-wizard"

    @GetMapping("grid$suf")
    fun grid():String = "admin/grid"

    @GetMapping("icon-fontawesome$suf")
    fun iconf():String = "admin/icon-fontawesome"

    @GetMapping("icon-material$suf")
    fun iconm():String = "admin/icon-material"

    @GetMapping("index2$suf")
    fun index2():String = "admin/index2"

    @GetMapping("pages-buttons$suf")
    fun button():String = "admin/pages-buttons"

    @GetMapping("pases-calendar$suf")
    fun calendar():String = "admin/pages-calendar"

    @GetMapping("pages-chat$suf")
    fun chat():String = "admin/pages-chat"

    @GetMapping("pages-elements$suf")
    fun elements():String = "admin/pages-elements"

    @GetMapping("pages-gallery$suf")
    fun gallery():String = "admin/pages-gallery"

    @GetMapping("pages-invoice$suf")
    fun invoice():String = "admin/pages-invoice"

    @GetMapping("tables$suf")
    fun tables():String = "admin/tables"

    @GetMapping("widgets$suf")
    fun widgets():String = "admin/widgets"

}