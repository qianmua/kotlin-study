package pres.hjc.kotlinspringboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.AuthModel
import pres.hjc.kotlinspringboot.mapping.AuthMapping
import pres.hjc.kotlinspringboot.service.AuthService

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/24
@time 13:07
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Service
class AuthServiceImpl :AuthService{
    @Autowired
    private lateinit var authMapping: AuthMapping

    override fun queryAllMenu(): List<AuthModel> = authMapping.queryAllMenu()

}