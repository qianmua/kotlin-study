package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.*
import pres.hjc.kotlinspringboot.entity.UserModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/16
@time 4:55
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
@Deprecated("userInfoMapping")
interface UserMapping {

    /**
     * 增强字符串
     */
    @Select("""
        select * from user where name = #{name} and password = #{password}
        """)
    fun login(@Param("name")name:String ,@Param("password")password:String):UserModel

    /*@Select("select * from user where name = #{name}")
    fun loadUsernameByMysql(@Param("name")name:String):UserDetails*/

    @Insert("insert into user(name,password) values (#{name},#{password})")
    @Options( useGeneratedKeys = true , keyColumn = "uid" ,keyProperty = "uid")
    fun insert(userModel: UserModel):Int
}