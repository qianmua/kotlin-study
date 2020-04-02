package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.*
import pres.hjc.kotlinspringboot.entity.SysLogModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/2
@time 11:03
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface SysLogMapping {

    @Select("select * from syslog")
    fun queryAll():MutableList<SysLogModel>?
    @Insert("""
            insert into syslog(username,operation,method,params,ip,browser,type,version,createdate) 
            values(#{username},#{operation},#{method},#{params},#{ip},
            #{browser},#{type},#{version},#{createdate})
        """)
    @Options( useGeneratedKeys = true, keyProperty = "lid" , keyColumn = "lid")
    fun add(logModel: SysLogModel):Int?
}