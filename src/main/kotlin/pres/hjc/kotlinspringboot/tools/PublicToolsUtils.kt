package pres.hjc.kotlinspringboot.tools

import java.net.InetAddress
import java.net.UnknownHostException
import javax.servlet.http.HttpServletRequest

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/28
@time 14:16
@version 1.0
To change this template use File | Settings | File Templates.
 */
object PublicToolsUtils {


    /**
     * 得到用户Ip
     */
    fun getIpAddress(request: HttpServletRequest):String{
        var ip:String = request.getHeader("x-forwarded-for")
        if (ip == null || ip.isEmpty() || "unknown".equals(ip,true)) {
            ip = request.getHeader("Proxy-Client-IP")
        }
        if (ip == null || ip.isEmpty()|| "unknown".equals(ip,true)) {
            ip = request.getHeader("WL-Proxy-Client-IP")
        }
        if (ip == null || ip.isEmpty() || "unknown".equals(ip,true)) {
            ip = request.getHeader("HTTP_CLIENT_IP")
        }
        if (ip == null || ip.isEmpty() || "unknown".equals(ip,true)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        }
        if (ip == null || ip.isEmpty() || "unknown".equals(ip,true)) {
            ip = request.remoteAddr
            if("127.0.0.1" == ip || "0:0:0:0:0:0:0:1" == ip){
                //根据网卡取本机配置的IP
                var  inet:InetAddress? = null
                try {
                    inet = InetAddress.getLocalHost()
                } catch (e: UnknownHostException) {
                    e.printStackTrace()
                }
                ip= inet!!.hostAddress
            }
        }
        return ip
    }

    /**
     * 得到用户浏览器类型
     */
    fun getBrowserType(request: HttpServletRequest):String{
        var type = "ie"
        val name:String = request.getHeader("USER-AGENT").toLowerCase()
        if (name.indexOf("msie") > 0) type = "ie"
        if (name.indexOf("firefox") > 0) type = "firefox"
        if (name.indexOf("chrome") > 0) type = "chrome"
        if (name.indexOf("opera") > 0) type = "opera"
        if (name.indexOf("gecko") > 0 && name.indexOf("rv:11")>0) type = "ie11"
        return type
    }

}