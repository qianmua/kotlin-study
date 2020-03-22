package pres.hjc.kotlinspringboot.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 16:34
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("fa")
class AdminForwardController {

    @GetMapping("/board")
    fun board(): String{
        return "admin/index"
    }
}