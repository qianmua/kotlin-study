package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import pres.hjc.kotlinspringboot.entity.ScheduledTaskModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 12:05
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface ScheduledTaskMapping {

    /**
     * 根据key 获取 任务信息
     */
    @Select("select task_key as taskKey,task_desc as taskDesc,task_cron as taskCron,init_start_flag as initStartFlag  from scheduled_task where task_key = '\${taskKey}' ")
    fun getByKey(@Param("taskKey") taskKey: String?): ScheduledTaskModel?

    /**
     * 获取程序初始化需要自启的任务信息
     */
    @Select("select task_key as taskKey,task_desc as taskDesc,task_cron as taskCron,init_start_flag as initStartFlag from scheduled_task where init_start_flag=1 ")
    fun getAllNeedStartTask(): List<ScheduledTaskModel?>?

    /**
     * 获取所有任务
     */
    @Select("select task_key as taskKey,task_desc as taskDesc,task_cron as taskCron,init_start_flag as initStartFlag  from scheduled_task ")
    fun getAllTask(): List<ScheduledTaskModel?>?
}