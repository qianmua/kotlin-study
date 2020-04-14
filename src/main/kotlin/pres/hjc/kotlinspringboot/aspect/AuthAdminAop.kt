package pres.hjc.kotlinspringboot.aspect

import org.apache.ibatis.session.SqlSessionFactory
import org.apache.ibatis.session.defaults.DefaultSqlSession
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
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
class AuthAdminAop {

    private val logger by lazy { LoggerFactory.getLogger(AuthAdminAop::class.java) }

    @Pointcut("execution(AuthMenuLeave)")
    fun auth(){}

    @Before("auth()")
    fun doBefore(joinPoint: JoinPoint){
    }
}