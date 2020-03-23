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
    fun index():String = "index"

    @GetMapping("index$suf")
    fun home():String = "index"

    @GetMapping("blog$suf")
    fun blog():String = "html/blog"

    @GetMapping("restaurant$suf")
    fun restaurant():String = "html/restaurant"

    @GetMapping("menu$suf")
    fun menu():String = "html/menu"

    @GetMapping("reservation$suf")
    fun res():String = "html/reservation"

    @GetMapping("contact$suf")
    fun cont():String = "html/contact"

    @GetMapping("task$suf")
    fun task():String = "html/shop"

    @GetMapping("gallery$suf")
    fun gallery():String = "html/gallery"

    @GetMapping("typography$suf")
    fun typo():String = "html/typography"

    @GetMapping("fullscreen-intro$suf")
    fun full():String = "html/fullscreen-intro"

    @GetMapping("top-intro$suf")
    fun top():String = "html/top-intro"

    @GetMapping("without-intro$suf")
    fun without():String = "html/without-intro"

}