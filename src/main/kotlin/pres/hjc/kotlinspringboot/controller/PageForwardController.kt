package pres.hjc.kotlinspringboot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/20
@time 5:53
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
class PageForwardController {

    @GetMapping("/")
    fun index():String{
        return "index"
    }
}