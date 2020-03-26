package pres.hjc.kotlinspringboot.controller.task

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/26
@time 23:16
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/fsi")
class ForwardSectionId {

    @GetMapping("")
    fun inits() = ""
}