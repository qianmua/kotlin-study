package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/24
@time 12:53
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 菜单组（一个菜单可能有多个权限级别）
 */
data class AuthModel(var aid:Long?, var url:String, var eid:Long?,var allRoles:List<MenuModel>?)