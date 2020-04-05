package pres.hjc.kotlinspringboot.handler

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import pres.hjc.kotlinspringboot.exception.BusinessException
import pres.hjc.kotlinspringboot.tools.APIResponse
import pres.hjc.kotlinspringboot.tools.APIResponse.Companion.fail

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/5
@time 20:51
@version 1.0
To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)


    @ExceptionHandler(value = [BusinessException::class])
    @ResponseBody
    fun businessException(e: Exception): APIResponse<*>? {
        var msg = "请求错误"
        if (e is BusinessException) {
            msg = e.getErrorCode()
        }
        logger.error("find exception:e={}", e.message)
        e.printStackTrace()
        return fail(msg)
    }
}