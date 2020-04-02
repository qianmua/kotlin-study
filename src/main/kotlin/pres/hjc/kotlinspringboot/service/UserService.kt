package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.UserModel
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/16
@time 4:51
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface UserService {

    fun addUserInfo(userModel: UserModel):Int?

    fun login(name:String , password:String):UserModel?

    fun queryById(uid:Int):UserModel?

    fun update(userModel: UserModel):Int?

    fun queryAdminId(name:String , password:String,request: HttpServletRequest,response:HttpServletResponse):UserModel?
}