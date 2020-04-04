package pres.hjc.kotlinspringboot.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.Signature
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import pres.hjc.kotlinspringboot.CustomLogTarget.Logs
import java.lang.reflect.Method
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/2
@time 10:46
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Aspect
class LogAdminAop {

    private val log = LoggerFactory.getLogger(LogAdminAop::class.java)

//    @Pointcut("execution(public * pres.hjc.kotlinspringboot.controller.admin..*.*(..))")
    @Pointcut("execution(Logs)")
    fun weblog(){}

    @Before("weblog()")
    fun doBefore(joinPoint: JoinPoint){
        try {
            val attributes: ServletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
            val request:HttpServletRequest  = attributes.request
            val session:HttpSession  = request.getSession(true)
            val email:String  = session.getAttribute("email").toString()
            //JoinPoint 对象可以获取目标业务方法的详细信息:方法签名,调用参数
            val m:Signature  = joinPoint.signature
            //方法名
            val methodName:String  = m.name
            val signature:MethodSignature  =  joinPoint.signature as MethodSignature
            val method:Method  = signature.method
            val logs:Logs  = method.getAnnotation(Logs::class.java)
            log.info("log value : ${logs.value}")
            log.info("operation man : $email the method : $methodName")
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}