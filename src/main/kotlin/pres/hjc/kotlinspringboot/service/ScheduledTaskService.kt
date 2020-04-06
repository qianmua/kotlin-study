package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.ScheduledTaskModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 12:44
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface ScheduledTaskService {
    /**
     * 所有任务列表
     */
    fun taskList(): List<ScheduledTaskModel?>?

    /**
     * 根据任务key 启动任务
     */
    fun start(taskKey: String?): Boolean?

    /**
     * 根据任务key 停止任务
     */
    fun stop(taskKey: String?): Boolean?

    /**
     * 根据任务key 重启任务
     */
    fun restart(taskKey: String?): Boolean?


    /**
     * 程序启动时初始化  ==> 启动所有正常状态的任务
     */
    fun initAllTask(scheduledTaskBeanList: List<ScheduledTaskModel?>?)

}