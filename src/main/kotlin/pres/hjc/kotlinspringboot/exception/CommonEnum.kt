package pres.hjc.kotlinspringboot.exception

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/10
@time 16:04
@version 1.0
To change this template use File | Settings | File Templates.
 */
public enum class CommonEnum constructor(private var resultCode:String,private var resultMsg:String):ExceptionBase {

    /**
     * 200
     */
    SUCCESS("200","请求成功"),
    /**400*/
    BODY_NOT_MATCH("400","格式错误"),
    /**403*/
    SERVICE_REJECT_REQUEST("403","拒绝请求"),
    /**500*/
    SERVICE_ERROR("500","服务器出错"),
    /**503*/
    SERVICE_BUSY("503","服务器正忙");

    override fun getErrorCode(): String  =resultCode
    override fun getErrorMsg(): String  = resultMsg

}