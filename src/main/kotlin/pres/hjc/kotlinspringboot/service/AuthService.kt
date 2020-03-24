package pres.hjc.kotlinspringboot.service

import pres.hjc.kotlinspringboot.entity.AuthModel

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/24
@time 13:07
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface AuthService {
    fun queryAllMenu():List<AuthModel>
}