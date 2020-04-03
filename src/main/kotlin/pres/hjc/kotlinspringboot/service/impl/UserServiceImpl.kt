package pres.hjc.kotlinspringboot.service.impl

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.ui.Model
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
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    private val log = LoggerFactory.getLogger(UserServiceImpl::class.java)

    override fun addUserInfo(userModel: UserModel): Int? {
        return userInfoMapping.addUserInfo(userModel)
    }

    override fun login(
            name: String,
            password: String): UserModel? {
        return userMapping.login(name, password)
    }

    override fun queryById(uid: Int): UserModel? {
        return userInfoMapping.queryById(uid)
    }

    override fun update(userModel: UserModel): Int? {
        return userInfoMapping.update(userModel)
    }

    /**
     * admin 登录
     */
    override fun queryAdminId(name: String,
                              password: String,
                              request: HttpServletRequest,
                              response:HttpServletResponse): UserModel? {
        var user:UserModel? = null
        var token:String? = CookieUtils.getCookie(request, ConstantUtils.SESSION_TOKEN)
        if (token != null) {
            val value = redisTemplate.opsForValue()[token]
            val json = JSONObject.toJSON(value)
            val toJOSNString = JSONObject.toJSONString(json)
            user = JSON.parseObject(toJOSNString,UserModel::class.java)
            redisTemplate.delete(token)
        }
        //没有该用户，阻断
        if (user == null) user = userInfoMapping.queryAdminId(name, password) ?: return null
        //重新生成token cookie 重新计时
        CookieUtils.removeCookie(response, ConstantUtils.SESSION_TOKEN)
        token = PublicToolsUtils.getUUID()
        redisTemplate.opsForValue().set(token,user)
        CookieUtils.addCookie(response,ConstantUtils.SESSION_TOKEN,token,60*60*24*7)
        return user
    }
}