package pres.hjc.kotlinspringboot.entity

import java.io.Serializable

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/16
@time 4:47
@version 1.0
To change this template use File | Settings | File Templates.
 */
data class UserModel(var uid:Long?,
                     var name:String? ,
                     var password:String?,
                     var birthday: String?,
                     var email: String?,
                     var tel: String?,
                     var realname:String?,
                     var classes:Int?,
                     var task:Int?,
                     var admin:Int?,
                     var version:Int?,
                     var stauts:Int?):Serializable