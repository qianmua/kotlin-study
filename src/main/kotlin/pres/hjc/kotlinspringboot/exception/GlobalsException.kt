package pres.hjc.kotlinspringboot.exception

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.mapping.ExceptionMapping
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/10
@time 15:59
@version 1.0
To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
class GlobalsException {
    @Autowired
    private lateinit var exception:ExceptionMapping

    private val logger by lazy { LoggerFactory.getLogger(GlobalsException::class.java) }

    @ExceptionHandler(value = [RunnerErrorHandle::class])
    @ResponseBody
    fun exceptionHandler(request:HttpServletRequest,e:RunnerErrorHandle?): ResultBody? {
        logger.error("自定义错误： ${e?.getErrorMsg()}")
        //service
        exception.add(e?.getErrorCode(),e?.getErrorMsg(),
                PublicToolsUtils.dateFormat(Date()),PublicToolsUtils.getIpAddress(request),1)
        return ResultBody.error(e?.getErrorCode(),e?.getErrorMsg())
    }

    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = [NullPointerException::class])
    @ResponseBody
    fun exceptionHandler(req: HttpServletRequest?, e: NullPointerException?): ResultBody? {
        logger.error("发生空指针异常！原因是: $e")
        //service
        exception.add(CommonEnum.BODY_NOT_MATCH.getErrorCode(),e.toString(),
                PublicToolsUtils.dateFormat(Date()),PublicToolsUtils.getIpAddress(req),1)
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH)
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun exceptionHandler(req: HttpServletRequest?, e: Exception?): ResultBody? {
        logger.error("其他异常！原因是: $e")
        //service
        exception.add(CommonEnum.SERVICE_ERROR.getErrorCode(),e.toString(),
                PublicToolsUtils.dateFormat(Date()),PublicToolsUtils.getIpAddress(req),1)
        return ResultBody.error(CommonEnum.SERVICE_ERROR)
    }
}