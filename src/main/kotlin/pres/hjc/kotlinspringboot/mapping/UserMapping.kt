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
interface UserMapping {

    @Select("select * from user where name = #{name} and password = #{password}")
    fun login(@Param("name")name:String ,@Param("password")password:String):UserModel

    @Insert("insert into user(name,password) values (#{name},#{password})")
    @Options( useGeneratedKeys = true , keyColumn = "uid" ,keyProperty = "uid")
    fun insert(userModel: UserModel):Int
}