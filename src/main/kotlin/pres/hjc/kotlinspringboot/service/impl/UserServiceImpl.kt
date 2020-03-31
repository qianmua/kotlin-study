package pres.hjc.kotlinspringboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.mapping.UserInfoMapping
import pres.hjc.kotlinspringboot.mapping.UserMapping
import pres.hjc.kotlinspringboot.service.UserService

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/16
@time 4:51
@version 1.0
To change this template use File | Settings | File Templates.
 */

@Service( value = "userService")
class UserServiceImpl(
        @Autowired
        private val userMapping: UserMapping):UserService {
    @Autowired
    private lateinit var userInfoMapping: UserInfoMapping

    override fun addUserInfo(userModel: UserModel): Int? {
        return userInfoMapping.addUserInfo(userModel)
    }

    override fun login(
            name: String,
            password: String): UserModel {
        return userMapping.login(name, password)!!
    }

    override fun queryById(uid: Int): UserModel? {
        return userInfoMapping.queryById(uid)
    }

    override fun update(userModel: UserModel): Int? {
        return userInfoMapping.update(userModel)
    }

}