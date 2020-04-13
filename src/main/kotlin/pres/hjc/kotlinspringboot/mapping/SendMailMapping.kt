package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.*
import pres.hjc.kotlinspringboot.entity.SendMailModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/13
@time 13:21
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface SendMailMapping {

    @Select("""
        select title,title2,formuser,touser,sendname,send,createdate,imgurl,status 
        from sendmail
        where mid=#{nid}
    """)
    fun queryById(@Param("mid")mid:Int):SendMailModel

    @Select("""
        select title,title2,formuser,touser,sendname,send,createdate,imgurl,status 
        from sendmail
    """)
    fun queryAll():MutableList<SendMailModel>

    @Insert("""
        insert into sendmail(select title,title2,formuser,touser,sendname,send,createdate,imgurl,status)
         values(#{title},#{title2},#{formuser},#{touser},
         #{sendname},#{send},#{createdate},#{imgurl},#{status})
    """)
    @Options( useGeneratedKeys =false, keyProperty = "mid" , keyColumn = "mid")
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