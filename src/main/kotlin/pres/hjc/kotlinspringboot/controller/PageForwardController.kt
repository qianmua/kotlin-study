package pres.hjc.kotlinspringboot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import pres.hjc.kotlinspringboot.tools.ConstantUtils

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/20
@time 5:53
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/ft")
class PageForwardController {

    companion object{
        private const val suf = ConstantUtils.suffix
    }

    @GetMapping("/")
    fun index():String{
        return "index"
    }
    @GetMapping("index$suf")
    fun menu():String{
        return "index"
    }
    @GetMapping("blog$suf")
    fun blog():String{
        return "html/blog"
    }
}