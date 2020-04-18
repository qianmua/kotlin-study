package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.GroupModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/16
@time 20:12
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface GroupMapping {

    @Select("""
        select * from groupClass
    """)
    fun queryAll():MutableList<GroupModel>

    @Select("""
        select * from groupClass where gid = #{gid}
    """)
    fun queryById(gid:Int):GroupModel

    @Select("""
        select * from groupClass where name = #{name}
    """)
    fun queryByName(name:String):GroupModel

    @Insert("""
        insert into groupClass(name,createTime,auth)
        values(#{name},#{createTime},#{auth})
        """)
    @Options( useGeneratedKeys = false , keyColumn = "gid" , keyProperty = "gid")
    fun add(groupModel: GroupModel):Unit

    @Update("""
        update 
        from groupClasss set name = #{name},set createTime = #{createTime},set auth = #{auth}
        where gid = #{gid}
        """)
    fun update(groupModel: GroupModel):Int

}