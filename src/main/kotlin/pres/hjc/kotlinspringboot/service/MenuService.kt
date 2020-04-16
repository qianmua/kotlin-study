package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.MenuModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/16
@time 21:11
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface MenuService {
    fun queryById(eid: Long): MenuModel
    fun queryAll():MutableList<MenuModel>
    fun add(roles:String):Int
}