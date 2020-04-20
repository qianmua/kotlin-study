package pres.hjc.kotlinspringboot.controller.admin

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl
import pres.hjc.kotlinspringboot.target.Logs
import pres.hjc.kotlinspringboot.tools.ConstantUtils
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/19
@time 21:28
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
class AdminLoginController {

    @Autowired
    private lateinit var userServiceImpl: UserServiceImpl
    /**
     * ajax login
     */
    @Logs("login")
    @RequestMapping("/login")
    @ResponseBody
    fun login(name:String,
              password:String,
              request: HttpServletRequest,
              response: HttpServletResponse,
              model: Model):String{
        if (name.length and password.length < 1 ){
            return "error"
        }else{
            //封装令牌
            val subject = SecurityUtils.getSubject()
            val usernamePasswordToken = UsernamePasswordToken(name, password)
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

    @RequestMapping("/unauth")
    @ResponseBody
    fun unAuth():String{
        return "error：401 未授权,请联系管理员获得权限"
    }

    @RequestMapping("/addUser")
    @ResponseBody
    fun addUser(userModel: UserModel):String{

        try {
            val status =  userServiceImpl.addUserInfo(userModel)
            if (status != 0 ){
                return "success"
            }else{
                return "fail"
            }
        } catch (e: Exception) {
            println("注册失败： message:" + e.message)
        }
        return "error"
    }
}