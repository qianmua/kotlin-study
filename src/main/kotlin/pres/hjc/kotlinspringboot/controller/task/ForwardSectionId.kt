package pres.hjc.kotlinspringboot.controller.task

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import pres.hjc.kotlinspringboot.entity.TaskModel
import javax.servlet.http.HttpServletRequest
import javax.websocket.server.PathParam

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

    @GetMapping("init")
    fun inits() = ""

    @PostMapping("add")
    @ResponseBody
    fun addTask(@PathParam("task") task:TaskModel ,
                request: HttpServletRequest,
                model:Model):Int{
        return 1
    }
}