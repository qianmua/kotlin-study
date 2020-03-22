package pres.hjc.kotlinspringboot.aspect

import com.alibaba.fastjson.JSONObject
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
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
    private val logger = LoggerFactory.getLogger(AllLogAop::class.java)

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

    @AfterReturning(returning = "resp" ,pointcut = "logaop()") //return
    fun doAfter(resp:Any) = afterAction(resp)

    private fun afterAction(resp:Any){
        /*val elapsedTime = System.currentTimeMillis() - logRecord.createAt!!.time
        logRecord.outParams = resp.toString()
        logRecord.status = 0
        logRecord.elapsedTime = elapsedTime
        logRecordRepo.save(logRecord)
        logger.info("ELAPSEDTIME: $elapsedTime")
        logThread.remove()*/
        logger.info("OUTPUT: $resp")
        logger.info("=============================FINISH============================")
    }

    @AfterThrowing(pointcut = "logaop()" ,throwing = "e")
    fun doAfterThrowing(joinPoint: JoinPoint ,e:Throwable){
        logger.info("exception method: ${joinPoint.target.javaClass.name}.${joinPoint.signature.name}()")
        logger.info("exception code: ${e.javaClass.name}")
        logger.info("exception msg: ${e.message}")
        /*val logRecord = logThread.get()
        logRecord.errMsg = GlobalConfig.api.getExceptionMsg(e)
        logRecord.status = -1
        logRecord.description = e.javaClass.name
        logRecordRepo.save(logRecord)
        logThread.remove()*/
        logger.info("=============================FINISH============================")
    }
}