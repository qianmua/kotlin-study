package pres.hjc.kotlinspringboot.tools

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/20
@time 1:44
@version 1.0
To change this template use File | Settings | File Templates.
 */
object CookieUtils {

    /**
     *  cookie tools
     *  add cookie
     */
    fun addCookie(request: HttpServletRequest,
                  response: HttpServletResponse,
                  name:String,
                  value:String,
                  maxAge:Int){
        val cookie:Cookie = Cookie(name, value)
        cookie.path = "/"
        if (maxAge > 0) cookie.maxAge
        response.addCookie(cookie)
    }

    /**
     * 删除Cookie
     */
    fun removeCookie(response: HttpServletResponse ,name:String){
        val uid:Cookie = Cookie(name,null)
        uid.path = "/"
        uid.maxAge = 0
        response.addCookie(uid)
    }

    /**
     * 得到cookie值
     */
    fun getCookie(request: HttpServletRequest, name:String):String?{
        val cookie:Array<Cookie> = request.cookies
        for (cookie in cookie) {
            if (cookie.name == name) return cookie.value
        }
        return null
    }

}