package pres.hjc.kotlinspringboot.aspect

import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.defaults.DefaultSqlSession
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import pres.hjc.kotlinspringboot.target.AuthMenuLeave

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/2
@time 19:23
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
class AuthAdminAop {

    private val logger by lazy { LoggerFactory.getLogger(AuthAdminAop::class.java) }

    @Pointcut("@annotation(pres.hjc.kotlinspringboot.target.AuthMenuLeave)")
    fun auth(){}

    @Before("auth()")
    fun doBefore(joinPoint: JoinPoint){
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++")
        val method = (joinPoint.signature as? MethodSignature)?.method
        val getaml = method?.getAnnotation(AuthMenuLeave::class.java)
        println(getaml?.value)
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++")
    }
}