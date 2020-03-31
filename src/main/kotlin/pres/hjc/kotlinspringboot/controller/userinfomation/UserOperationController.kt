package pres.hjc.kotlinspringboot.controller.userinfomation

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.mapping.UserInfoMapping

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
}