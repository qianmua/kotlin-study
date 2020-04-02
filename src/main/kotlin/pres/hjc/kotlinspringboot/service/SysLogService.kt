package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.SysLogModel
import javax.servlet.http.HttpServletRequest

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/2
@time 11:13
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface SysLogService {
    fun queryAll():MutableList<SysLogModel>?
    fun add(operation:String, request: HttpServletRequest):Int?
}