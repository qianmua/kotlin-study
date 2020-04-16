package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.*
import pres.hjc.kotlinspringboot.entity.MenuModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/24
@time 14:22
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface MenuMapping {

    @Select("select roles form menu where eid = #{eid}")
    fun queryById(eid: Long):MenuModel

    @Select("""
        select * from menu
    """)
    fun queryAll():MutableList<MenuModel>

    @Insert("""
        insert into roles values(#{roles})
    """)
    @Options( useGeneratedKeys = false , keyProperty = "eid", keyColumn = "eid")
    fun add(roles:String):Int

}