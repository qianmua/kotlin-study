package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/10
@time 18:33
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 错误异常表
 */
data class ExceptionModel(var exid:Long? ,
                          var errCode:String?,
                          var errMsg:String?,
                          var createTime:String?,
                          var triggerIp:String?,
                          var status:Int?)