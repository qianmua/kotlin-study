package pres.hjc.kotlinspringboot.service

import org.apache.ibatis.annotations.Param
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

    fun add(@Param("title")title:String,
            @Param("title2")title2:String,
            @Param("formuser")formuser:String,
            @Param("touser")touser:String,
            @Param("sendname")sendname:String,
            @Param("send")send:String,
            @Param("imgurl")imgurl:String,
            @Param("createdate")createdate:String,
            @Param("title")status:Int):Int
}