//package pres.hjc.kotlinspringboot.interceptor.security.handler
//
//import org.springframework.security.access.AccessDeniedException
//import org.springframework.security.web.access.AccessDeniedHandler
//import java.io.PrintWriter
//import javax.servlet.http.HttpServletRequest
//import javax.servlet.http.HttpServletResponse
//
///**
//Created by IntelliJ IDEA.
//@author HJC
//@date 2020/3/22
//@time 19:06
//@version 1.0
//To change this template use File | Settings | File Templates.
// */
//
///**
// * @Author: Galen
// * @Date: 2019/3/27-17:36
// * @Description: Denied是拒签的意思
// * 此处我们可以自定义403响应的内容,让他返回我们的错误逻辑提示
// **/
//class AccessDeniedHandler:AccessDeniedHandler {
//    override fun handle(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: AccessDeniedException?) {
//        p1!!.status = HttpServletResponse.SC_FORBIDDEN
//        val write:PrintWriter? = p1.writer
//        write!!.write("权限不足")
//        write.flush()
//        write.close()
//    }
//
//
//}