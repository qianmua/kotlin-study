package pres.hjc.kotlinspringboot.typeset

import pres.hjc.kotlinspringboot.function.task.ScheduledTaskJob
import pres.hjc.kotlinspringboot.function.task.impl.ScheduledTask_01
import pres.hjc.kotlinspringboot.function.task.impl.ScheduledTask_02
import pres.hjc.kotlinspringboot.function.task.impl.ScheduledTask_03
import java.util.concurrent.ConcurrentHashMap


/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 12:14
@version 1.0
To change this template use File | Settings | File Templates.
 */
enum class ScheduledTaskEnum(taskKey: String, scheduledTaskJob: ScheduledTaskJob) {
    /**
     * 任务1
     */
    TASK_01("scheduledTask01", ScheduledTask_01()),

    /**
     * 任务2
     */
    TASK_02("scheduledTask02", ScheduledTask_02()),

    /**
     * 任务3
     */
    TASK_03("scheduledTask03", ScheduledTask_03());

    /**
     * 定时任务key
     */
    private val taskKey: String

    /**
     * 定时任务 执行实现类
     */
    private val scheduledTaskJob: ScheduledTaskJob

    companion object {
        /**
         * 初始化 所有任务
         */
        fun initScheduledTask(): Map<String, ScheduledTaskJob> {
            if (values().size < 0) {
                return ConcurrentHashMap()
            }
            val scheduledTaskJobMap: MutableMap<String, ScheduledTaskJob> = ConcurrentHashMap()
            for (taskEnum in values()) {
                scheduledTaskJobMap[taskEnum.taskKey] = taskEnum.scheduledTaskJob
            }
            return scheduledTaskJobMap
        }
    }
    init {
        this.taskKey = taskKey
        this.scheduledTaskJob = scheduledTaskJob
    }
}