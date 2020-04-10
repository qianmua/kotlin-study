package pres.hjc.kotlinspringboot.entity

import java.io.Serializable

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/20
@time 4:32
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 任务表
 */
data class TaskModel(var tid:Int?,
                     var task:String?,
                     var title:String?,
                     var fromto:String?,
                     var starttime:String?,
                     var endtime:String?,
                     var uid:Int?,
                     var status:Int?):Serializable