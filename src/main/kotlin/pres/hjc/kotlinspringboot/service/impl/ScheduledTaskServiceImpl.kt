package pres.hjc.kotlinspringboot.service.impl

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.Trigger
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.support.CronTrigger
import org.springframework.stereotype.Service
import org.springframework.util.CollectionUtils
import pres.hjc.kotlinspringboot.entity.ScheduledTaskModel
import pres.hjc.kotlinspringboot.function.task.ScheduledTaskJob
import pres.hjc.kotlinspringboot.mapping.ScheduledTaskMapping
import pres.hjc.kotlinspringboot.service.ScheduledTaskService
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.locks.ReentrantLock


/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 12:45
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Service
class ScheduledTaskServiceImpl:ScheduledTaskService{

    private val LOGGER by lazy { LoggerFactory.getLogger(ScheduledTaskServiceImpl::class.java) }

    @Autowired
    private lateinit var scheduledTaskMapping:ScheduledTaskMapping
    /**
     * 定时任务线程池
     * Qualifier指定bean
     */
    @Qualifier("threadTask")
    @Autowired
    private lateinit var threadPoolTaskScheduler: ThreadPoolTaskScheduler
    /**
     * 可重入锁
     */
    private val lock = ReentrantLock()

    /**
     * 所有定时任务存放Map
     * key :任务 key
     * value:任务实现
     */
    @Autowired
    @Qualifier(value = "scheduledTaskJobMap")
    private val scheduledTaskJobMap: Map<String, ScheduledTaskJob>? = null

    /**
     * 存放已经启动的任务map
     */
    private val scheduledFutureMap: Map<String?, ScheduledFuture<*>> = ConcurrentHashMap<String?, ScheduledFuture<*>>()

    /**
     * 所有任务列表
     */
    override fun taskList(): List<ScheduledTaskModel?>? {
        LOGGER.info(">>>>>> 获取任务列表开始 >>>>>> ")
        //数据库查询所有任务 => 未做分页
        val taskBeanList: List<ScheduledTaskModel?>? = scheduledTaskMapping.getAllTask()
        if (CollectionUtils.isEmpty(taskBeanList)) {
            return ArrayList<ScheduledTaskModel>()
        }
        for (taskBean in taskBeanList!!) {
            val taskKey: String? = taskBean?.taskKey
            //是否启动标记处理
            taskBean?.startFlag = this.isStart(taskKey)
        }
        LOGGER.info(">>>>>> 获取任务列表结束 >>>>>> ")
        return taskBeanList
    }


    /**
     * 根据任务key 启动任务
     */
    override fun start(taskKey: String?): Boolean {
        LOGGER.info(">>>>>> 启动任务 {} 开始 >>>>>>", taskKey)
        //添加锁放一个线程启动，防止多人启动多次
        lock.lock()
        LOGGER.info(">>>>>> 添加任务启动锁完毕")
        try {
            //校验是否已经启动
            if (taskKey?.let { isStart(it) }!!) {
                LOGGER.info(">>>>>> 当前任务已经启动，无需重复启动！")
                return false
            }
            //校验任务是否存在
            if (!scheduledTaskJobMap!!.containsKey(taskKey)) {
                return false
            }
            //根据key数据库获取任务配置信息
            val scheduledTask: ScheduledTaskModel? = scheduledTaskMapping.getByKey(taskKey)
            //启动任务
            scheduledTask?.let { doStartTask(it) }
        } finally {
            // 释放锁
            lock.unlock()
            LOGGER.info(">>>>>> 释放任务启动锁完毕")
        }
        LOGGER.info(">>>>>> 启动任务 {} 结束 >>>>>>", taskKey)
        return true
    }

    /**
     * 根据 key 停止任务
     */
    override fun stop(taskKey: String?): Boolean? {
        LOGGER.info(">>>>>> 进入停止任务 {}  >>>>>>", taskKey)
        //当前任务实例是否存在
        val taskStartFlag = scheduledFutureMap.containsKey(taskKey)
        LOGGER.info(">>>>>> 当前任务实例是否存在 {}", taskStartFlag)
        if (taskStartFlag) {
            //获取任务实例
            val scheduledFuture: ScheduledFuture<*>? = scheduledFutureMap[taskKey!!]
            //关闭实例
            scheduledFuture!!.cancel(true)
        }
        LOGGER.info(">>>>>> 结束停止任务 {}  >>>>>>", taskKey)
        return taskStartFlag
    }

    /**
     * 根据任务key 重启任务
     */
    override fun restart(taskKey: String?): Boolean? {
        LOGGER.info(">>>>>> 进入重启任务 {}  >>>>>>", taskKey)
        //先停止
        stop(taskKey)
        //再启动
        return this.start(taskKey)
    }

    /**
     * 程序启动时初始化  ==> 启动所有正常状态的任务
     */
    override fun initAllTask(scheduledTaskBeanList: List<ScheduledTaskModel?>?) {
        LOGGER.info("程序启动 ==> 初始化所有任务开始 ！size={}", scheduledTaskBeanList?.size)
        if (CollectionUtils.isEmpty(scheduledTaskBeanList)) {
            return
        }
        for (scheduledTask in scheduledTaskBeanList!!) {
            //任务 key
            val taskKey: String? = scheduledTask?.taskKey
            //校验是否已经启动
            if (taskKey?.let { isStart(it) }!!) {
                continue
            }
            //启动任务
            scheduledTask?.let { doStartTask(it) }
        }
        LOGGER.info("程序启动 ==> 初始化所有任务结束 ！size={}", scheduledTaskBeanList.size)
    }

    /**
     * 执行启动任务
     */
    private fun doStartTask(scheduledTask: ScheduledTaskModel) {
        //任务key
        val taskKey: String? = scheduledTask.taskKey
        //定时表达式
        val taskCron: String? = scheduledTask.taskCron
        //获取需要定时调度的接口
        val scheduledTaskJob = scheduledTaskJobMap!![taskKey]
        LOGGER.info(">>>>>> 任务 [ {} ] ,cron={}", scheduledTask.taskDesc, taskCron)
        val scheduledFuture = threadPoolTaskScheduler.schedule(scheduledTaskJob!!,
                Trigger { triggerContext ->
                    val cronTrigger = taskCron?.let { CronTrigger(it) }
                    cronTrigger?.nextExecutionTime(triggerContext)
                })
        //将启动的任务放入 map

        LOGGER.info("定时任务：${taskKey} when : $scheduledFuture")
//        scheduledFutureMap.plus(taskKey,scheduledFuture)
    }

    /**
     * 任务是否已经启动
     */
    private fun isStart(taskKey: String?): Boolean {
        //校验是否已经启动
        if (scheduledFutureMap.containsKey(taskKey)) {
            if (!scheduledFutureMap[taskKey]?.isCancelled!!) {
                return true
            }
        }
        return false
    }

}