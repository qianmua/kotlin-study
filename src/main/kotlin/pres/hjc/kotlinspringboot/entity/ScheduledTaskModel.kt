package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 12:02
@version 1.0
To change this template use File | Settings | File Templates.
 */
data class ScheduledTaskModel(var taskKey:String?,
                              var taskDesc:String?,
                              var taskCron:String?,
                              var initStartFlag:Int?,
                              var startFlag:Boolean?)