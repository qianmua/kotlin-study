package pres.hjc.kotlinspringboot.function.task.impl

import org.slf4j.LoggerFactory
import pres.hjc.kotlinspringboot.function.task.ScheduledTaskJob

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 12:09
@version 1.0
To change this template use File | Settings | File Templates.
 */

class ScheduledTask_03:ScheduledTaskJob {
    private val log by lazy { LoggerFactory.getLogger(ScheduledTask_03::class.java) }


    override fun run() {
        log.info("ScheduledTask => 03  run  当前线程名称 ${Thread.currentThread().name}")
    }

    

}