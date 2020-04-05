package pres.hjc.kotlinspringboot.tools

/**
 * 返回的参数封装类
 * Created by Donghua.Chen on 2018/4/20.
 */
class APIResponse<T> {
    var code: String? = null
    var data: T? = null
        private set
    var msg: String? = null

    constructor() {}
    constructor(code: String?) {
        this.code = code
    }

    constructor(code: String?, data: T) {
        this.code = code
        this.data = data
    }

    constructor(code: String?, msg: String?) {
        this.code = code
        this.msg = msg
    }

    fun setData(data: T) {
        this.data = data
    }

    companion object {
        private const val CODE_SUCCESS = "success"
        private const val CODE_FAIL = "fail"
        fun success(): APIResponse<*> {
            return APIResponse<Any?>(CODE_SUCCESS)
        }

        fun success(data: Any?): APIResponse<*> {
            return APIResponse(CODE_SUCCESS, data)
        }

        fun fail(msg: String?): APIResponse<*> {
            return APIResponse<Any?>(CODE_FAIL, msg)
        }

        fun widthCode(errorCode: String?): APIResponse<*> {
            return APIResponse<Any?>(errorCode)
        }
    }
}