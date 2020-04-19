package pres.hjc.kotlinspringboot.controller.userinfomation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.mapping.UserInfoMapping
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl
import pres.hjc.kotlinspringboot.target.AuthMenuLeave

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/31
@time 22:12
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/uinfo")
class UserOperationController {
    @Autowired
    private lateinit var userInfoMapping: UserInfoMapping

    @Autowired
    private lateinit var userServiceImpl: UserServiceImpl

    @GetMapping("ti")
    @ResponseBody
    fun testjog():String{
        val userModel = userInfoMapping.queryById(1)
        println(userModel.toString())

        userModel?.password = "wocao6666"
        val versionUpdate1 = userInfoMapping.update(userModel)
        userModel?.password = "niubia1"
        val versionUpdate2 = userInfoMapping.update(userModel)
        println("第一次修改${if (versionUpdate1 == 1) "成功" else "失败"}")
        println("第二次修改${if (versionUpdate2 == 1) "成功" else "失败"}")
        return "?"
    }

    @AuthMenuLeave("100")
    @PostMapping("addUser")
    @ResponseBody
    fun addUser(@RequestBody userModel: UserModel):String{
        /*验证参数*/
        if (userModel == null) "错误的注册方式"
        if (userModel.name == null  || userModel.password == null) "错误的用户名"
        if (userModel.name!!.length < 5   || userModel.password!!.length < 5) "格式有误"
        val addUserInfo = userServiceImpl.addUserInfo(userModel)
        return ""
    }
}