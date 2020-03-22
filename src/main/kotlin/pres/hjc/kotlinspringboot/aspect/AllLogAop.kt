package pres.hjc.kotlinspringboot.aspect

import com.alibaba.fastjson.JSONObject
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.logging.LogRecord

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 16:42
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Aspect
class AllLogAop {

    //@Autowired
    val logger = LoggerFactory.getLogger(AllLogAop::class.java)

    @Pointcut("execution(public * pres.hjc.kotlinspringboot.controller.task..*.*(..))")
    fun logaop(){}

    @Before("logaop()")
    fun doBefore(joinPoint: JoinPoint) = beforeAction(joinPoint,"log")


    private fun beforeAction(joinPoint: JoinPoint,aop:String){
        val attr = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
        val request = attr.request
        // 记录内容
        val classMethod = "${joinPoint.target.javaClass.name}.${joinPoint.signature.name}()"
        val uri = request.requestURI
        val reqMethod = request.method
        val ip = /*GlobalConfig.api.getRemoteIp(request)*/ "localhost"
        val ipAddr = if (ip == null) "" else ip!! /*如果为空 抛出异常*/
        val params = if (request.parameterMap.isNotEmpty()) JSONObject.toJSONString(request.parameterMap) else ""
        logger.info("=============================START============================")
        logger.info("REQ CLASS: $classMethod")
        logger.info("URI: $uri")
        logger.info("HTTP METHOD: $reqMethod")
        logger.info("IP: $ipAddr")
        logger.info("HTTP INPUT: $params")
        /*val logRecord = LogRecord(classMethod, uri, reqMethod, ipAddr)
        try {
            logRecord.inParams = params
            logRecordRepo.save(logRecord)
            logThread.set(logRecord)
        } catch (e: Exception) {
            logger.error("$aopType aop error", e)
            logRecord.status = -1
            logRecord.errMsg = e.message
            logRecord.description = "occur error"
            logRecordRepo.save(logRecord)
        }*/
    }

    @AfterReturning(returning = "resp" ,pointcut = "logaop()")
    fun doAfter(resp:Any) = afterAction(resp)

    fun afterAction(resp:Any){

    }
}