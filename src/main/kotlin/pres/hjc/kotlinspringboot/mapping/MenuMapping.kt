package pres.hjc.kotlinspringboot.mapping

import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import pres.hjc.kotlinspringboot.entity.MenuModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/24
@time 14:22
@version 1.0
To change this template use File | Settings | File Templates.
 */

interface MenuMapping {

    @Select("select mid,roles form menus where mid = #{mid}")
    fun queryById(@Param("mid")mid: Long):List<MenuModel>

}