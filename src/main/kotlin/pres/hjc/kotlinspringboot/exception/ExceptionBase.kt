package pres.hjc.kotlinspringboot.exception

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/10
@time 16:00
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface ExceptionBase {
    /**error code*/
    fun getErrorCode():String
    /**错误信息*/
    fun getErrorMsg():String

}