package pres.hjc.kotlinspringboot.controller.task

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import pres.hjc.kotlinspringboot.service.impl.ScheduledTaskServiceImpl


/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 13:43
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/scheduled")
class ScheduledController {

    @Autowired
    private lateinit var scheduledTaskService:ScheduledTaskServiceImpl

    /**
     * 根据任务key => 启动任务
     */
    @RequestMapping("/start")
    fun start(@RequestParam("taskKey") taskKey: String?): String? {
        scheduledTaskService.start(taskKey)
        return "start success"
    }

    /**
     * 根据任务key => 停止任务
     */
    @RequestMapping("/stop")
    fun stop(@RequestParam("taskKey") taskKey: String?): String? {
        scheduledTaskService.stop(taskKey)
        return "stop success"
    }

    /**
     * 根据任务key => 重启任务
     */
    @RequestMapping("/restart")
    fun restart(@RequestParam("taskKey") taskKey: String?): String? {
        scheduledTaskService.restart(taskKey)
        return "restart success"
    }
}