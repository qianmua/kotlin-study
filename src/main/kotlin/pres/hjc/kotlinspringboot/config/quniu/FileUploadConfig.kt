package pres.hjc.kotlinspringboot.config.quniu

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/1
@time 16:10
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Component
@ConfigurationProperties(prefix = "upload")
class FileUploadConfig {

    /**
     * 本地上传，预留接口
     */
    private val local: Local = Local()
    public fun getLocal() = local
    public class Local

    /**
     * 七牛云储存
     */
    private val qiniu:Qiniu = Qiniu()
    fun getQiniu() = qiniu
    data class Qiniu(var domain: String? = "N", var accessKey: String? = "N",
                     var secretKey: String? = "N" , var bucket:String? = "N")


}