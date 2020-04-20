package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.*
import pres.hjc.kotlinspringboot.entity.UserModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/31
@time 21:31
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface UserInfoMapping {

    @Insert("""
        insert into userinfo(name,password,email,tel,version) values(
        #{name},#{password},#{email},#{tel},#{version}
        )
    """)
    fun addUserInfo(userModel: UserModel?):Int?

    @Select("""
        select * from userinfo where uid=#{uid}
    """)
    fun queryById(@Param("uid")uid:Int?):UserModel?


    @Update("""
        update userinfo set password = #{password} ,version = version+1
        where uid = #{uid} and version = #{version}
    """)
    fun update(userModel: UserModel?):Int?

    @Select("select * from userinfo where name = #{name} and password = #{password} and admin > 0")
    fun queryAdminId(@Param("name")name:String, @Param("password")password:String):UserModel?

    @Select("""
        
    """)
    fun queryByName();
}