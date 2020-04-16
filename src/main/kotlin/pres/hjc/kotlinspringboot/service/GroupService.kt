package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.GroupModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/16
@time 20:45
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface GroupService {
    fun queryAll():MutableList<GroupModel>
    fun queryById(gid:Int):GroupModel
    fun queryByName(name:String):GroupModel
    fun add(groupModel: GroupModel):Unit
    fun update(groupModel: GroupModel):Int
}