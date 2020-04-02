package pres.hjc.kotlinspringboot.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory

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

    @Pointcut("execution(public * pres.hjc.kotlinspringboot.controller.admin..*.*(..))")
    fun weblog(){}

    @Before("weblog()")
    fun doBefore(joinPoint: JoinPoint){
        log.info("admin.test")
    }
}