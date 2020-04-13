package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.SendMailModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/13
@time 13:47
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface SendMailService {

    fun queryAll():MutableList<SendMailModel>

    fun queryById(mid:Int):SendMailModel

    fun add(title:String,title2:String):Int
}