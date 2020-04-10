package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import pres.hjc.kotlinspringboot.entity.ExceptionModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/10
@time 19:39
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface ExceptionMapping {
    @Insert("""
        insert gloexception(err_code,err_msg,createtime,trigger_ip,status) 
        values(#{errCode},#{errMsg},#{createTime},#{triggerIp},#{status}) 
        """)
    @Options( useGeneratedKeys = false, keyColumn = "exid" ,keyProperty = "exid")
    fun add(@Param("errCode")errCode:String?,
            @Param("errMsg")errMsg:String?,
            @Param("createTime")createTime:String?,
            @Param("triggerIp")triggerIp:String?,
            @Param("status")status:Int):Int
}