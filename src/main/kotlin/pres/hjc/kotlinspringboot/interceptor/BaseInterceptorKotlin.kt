package pres.hjc.kotlinspringboot.interceptor

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import pres.hjc.kotlinspringboot.entity.UserModel
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/5
@time 20:36
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Component
class BaseInterceptorKotlin:HandlerInterceptor {
    private val LOGGE = LoggerFactory.getLogger(BaseInterceptorKotlin::class.java)
    private val USER_AGENT = "user-agent"
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, o: Any): Boolean {
        val uri = request.requestURI
        /*LOGGE.info("用户请求头: {}", request.getHeader(USER_AGENT))
        LOGGE.info("请求地址: $uri , 访问IP : ${PublicToolsUtils.getIpAddress(request)}")*/
        val userModel = request.session.getAttribute("user")
        if (uri.startsWith("/admin") &&
                !uri.startsWith("/admin/login") &&
                null == userModel &&
                !uri.startsWith("/admin/css") &&
                !uri.startsWith("/admin/images")
                && !uri.startsWith("/admin/js") &&
                !uri.startsWith("/admin/plugins")
                && !uri.startsWith("/admin/editormd")) {
            response.sendRedirect(request.contextPath + "/admin/login")
            return false
        }
        return true
    }
    override fun afterCompletion(httpServletRequest: HttpServletRequest,
                                 httpServletResponse: HttpServletResponse,
                                 o: Any, e: Exception?) {
    }

}