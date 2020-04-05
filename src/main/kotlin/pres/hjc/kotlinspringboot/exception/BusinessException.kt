package pres.hjc.kotlinspringboot.exception

import org.slf4j.LoggerFactory
import pres.hjc.kotlinspringboot.tools.APIResponse
import java.text.MessageFormat

/**
 * 统一异常类
 * Created by Donghua.Chen on 2018/4/20.
 */
class BusinessException : RuntimeException {
    private var errorCode: String
    private var errorMessageArguments: Array<String?>
    private var apiResponse: APIResponse<*>? = null

    private constructor() : this("") {}
    constructor(message: String?) : super(message) {
        errorCode = "fail"
        errorMessageArguments = arrayOfNulls(0)
    }

    constructor(message: String?, cause: Throwable?) : super(message, cause) {
        errorCode = "fail"
        errorMessageArguments = arrayOfNulls(0)
    }

    fun getErrorCode(): String {
        return errorCode
    }

    fun setErrorCode(errorCode: String) {
        this.errorCode = errorCode
    }

    private fun getErrorMessageArguments(): Array<String?>? {
        return errorMessageArguments
    }

    fun setErrorMessageArguments(errorMessageArguments: Array<String?>) {
        this.errorMessageArguments = errorMessageArguments
    }

    fun withErrorMessageArguments(vararg errorMessageArguments: String?): BusinessException {
        this.errorMessageArguments = errorMessageArguments as Array<String?>
        return this
    }

    fun response(): APIResponse<*>? {
        return if (apiResponse != null) {
            apiResponse
        } else {
            apiResponse = APIResponse.widthCode(getErrorCode())
            if (getErrorMessageArguments() != null && getErrorMessageArguments()!!.size > 0) {
                try {
                    apiResponse.msg = "apiResponse消息" // MessageFormat.format(apiResponse.msg, getErrorMessageArguments())
                } catch (var2: Exception) {
                    logger.error(var2.message)
                }
            }
            apiResponse
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BusinessException::class.java)
        fun withErrorCode(errorCode: String): BusinessException {
            val businessException = BusinessException()
            businessException.errorCode = errorCode
            return businessException
        }

        fun fromAPIResponse(apiResponse: APIResponse<*>?): BusinessException {
            var apiResponse = apiResponse
            val businessException = BusinessException()
            if (apiResponse == null) {
                apiResponse = APIResponse.fail("NULL")
            }
            businessException.apiResponse = apiResponse
            return businessException
        }
    }
}