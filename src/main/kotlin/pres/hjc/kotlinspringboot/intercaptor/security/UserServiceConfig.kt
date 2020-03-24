package pres.hjc.kotlinspringboot.intercaptor.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 18:54
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Transactional
@Service
class UserServiceConfig {
    @Autowired
    private lateinit var userService:UserServiceImpl

    fun loadUser(name:String, password:String):UserModel{
        return userService.login(name, password)
    }

}