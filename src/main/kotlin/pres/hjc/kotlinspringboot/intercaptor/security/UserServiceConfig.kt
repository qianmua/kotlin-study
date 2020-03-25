package pres.hjc.kotlinspringboot.intercaptor.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.mapping.UserMapping
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
class UserServiceConfig:UserDetailsService {
    @Autowired
    private lateinit var userService:UserMapping

    fun loadUser(name:String, password:String):UserModel{
        return userService.login(name, password)
    }

    /**
     * @Author: Galen
     * @Description: 实现了UserDetailsService接口中的loadUserByUsername方法
     * 执行登录,构建Authentication对象必须的信息,
     * 如果用户不存在，则抛出UsernameNotFoundException异常
     * @Date: 2019/3/27-16:04
     * @Param: [s]
     * @return: org.springframework.security.core.userdetails.UserDetails
     **/
    override fun loadUserByUsername(p0: String): UserDetails {
        return userService.loadUsernameByMysql(p0)
    }

}