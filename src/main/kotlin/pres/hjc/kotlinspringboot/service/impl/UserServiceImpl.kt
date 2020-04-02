package pres.hjc.kotlinspringboot.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.mapping.UserInfoMapping
import pres.hjc.kotlinspringboot.mapping.UserMapping
import pres.hjc.kotlinspringboot.service.UserService
import pres.hjc.kotlinspringboot.tools.ConstantUtils
import pres.hjc.kotlinspringboot.tools.CookieUtils
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/16
@time 4:51
@version 1.0
To change this template use File | Settings | File Templates.
 */

@Service( value = "userService")
class UserServiceImpl:UserService {
    @Autowired
    private lateinit var userInfoMapping: UserInfoMapping
    @Autowired
    private lateinit var userMapping: UserMapping

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

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

    override fun queryAdminId(name: String,
                              password: String,
                              request: HttpServletRequest,
                              response:HttpServletResponse): UserModel? {
        var user:UserModel? = null
        var token:String? = CookieUtils.getCookie(request, ConstantUtils.SESSION_TOKEN)
        if (token != null) {
            user = redisTemplate.opsForValue()[token + ""] as UserModel
            redisTemplate.delete(token + "")
        }
        if (user == null) user = userInfoMapping.queryAdminId(name, password)
        //重新生成token cookie 重新计时
        CookieUtils.removeCookie(response, ConstantUtils.SESSION_TOKEN)
        token = PublicToolsUtils.getUUID()
        redisTemplate.opsForValue().set(token,user ?: "not null")
        CookieUtils.addCookie(response,ConstantUtils.SESSION_TOKEN,token,60*60*24*7)
        return user
    }
}