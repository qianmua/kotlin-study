package pres.hjc.kotlinspringboot.config.task

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import pres.hjc.kotlinspringboot.function.task.ScheduledTaskJob
import pres.hjc.kotlinspringboot.typeset.ScheduledTaskEnum

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 11:49
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Configuration
class ScheduledTaskConfig {

    private val log by lazy { LoggerFactory.getLogger(ScheduledTaskConfig::class.java) }

    /**
     * 创建定时任务线程池
     */
    @Bean("threadTask")
    fun threadTask():ThreadPoolTaskScheduler{
        log.info("创建定时任务线程池 start......")
        val thread = ThreadPoolTaskScheduler()
        //线程池大小
        thread.poolSize = 15
        thread.setThreadNamePrefix("taskExecutor-")
        thread.setWaitForTasksToCompleteOnShutdown(true)
        thread.setAwaitTerminationSeconds(60)
        log.info("创建结束 ending.....")
        return thread
    }

    /**
     * 初始化任务map
     */
    @Bean("scheduledTaskJobMap")
    fun scheduledTaskJobMap():Map<String, ScheduledTaskJob>{
        return ScheduledTaskEnum.initScheduledTask()
    }
}