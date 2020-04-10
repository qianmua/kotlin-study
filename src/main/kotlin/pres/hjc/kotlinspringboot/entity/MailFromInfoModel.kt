package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/7
@time 20:17
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 邮件管理员表（发件人）
 */
data class MailFromInfoModel(var mailId:Long,var fromName:String? ,var fromMail:String?,var status:Int)