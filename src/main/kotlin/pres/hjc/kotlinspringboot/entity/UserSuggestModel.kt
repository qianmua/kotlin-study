package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/6
@time 14:15
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 用户建议表
 */
data class UserSuggestModel(var sugId:Long?,
                            var sug:String?,
                            var sug_type:String?,
                            var sug_url:String?,
                            var sug_date:String?,
                            var qqAddress:String?,
                            var status:Int?)