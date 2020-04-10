package pres.hjc.kotlinspringboot.entity

import java.io.Serializable


/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/30
@time 19:57
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 系统日志
 */
data class SysLogModel (
        var lid:Long? = -1 ,
        var username:String? ,
        var operation:String? ,
        var method:String? ,
        var params:String?,
        var ip:String? ,
        var browser:String?,
        var type:String?,
        var version:String?,
        var createdate:String?):Serializable