package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/30
@time 20:47
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 邮件表
 */
data class SendMailModel (var mid:Long? ,
                     var title:String?,
                     var title2:String?,
                     var formuser:String? ,
                     var touser:String? ,
                     var sendname:String? ,
                     var send:String?,
                     var createdate:String?,
                     var imgurl:String?,
                     var status:Int? )