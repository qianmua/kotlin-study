package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import pres.hjc.kotlinspringboot.entity.AuthModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/24
@time 12:57
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Mapper
interface AuthMapping {

    @Select(" select aid,url,roles from menus , auths")
    fun queryAllMenu():List<AuthModel>

}