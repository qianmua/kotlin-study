package pres.hjc.kotlinspringboot.exception

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
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

    private val logger by lazy { LoggerFactory.getLogger(GlobalsException::class.java) }

    @ExceptionHandler(value = [RunnerErrorHandle::class])
    @ResponseBody
    fun exceptionHandler(request:HttpServletRequest,e:RunnerErrorHandle?): ResultBody? {
        logger.info("自定义错误： ${e?.getErrorMsg()}")
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
        return ResultBody.error(CommonEnum.SERVICE_ERROR)
    }
}