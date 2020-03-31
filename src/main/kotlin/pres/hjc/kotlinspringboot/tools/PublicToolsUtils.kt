package pres.hjc.kotlinspringboot.tools

import eu.bitwalker.useragentutils.Browser
import eu.bitwalker.useragentutils.OperatingSystem
import eu.bitwalker.useragentutils.UserAgent
import eu.bitwalker.useragentutils.Version
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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
    fun getIpAddress(request: HttpServletRequest):String?{
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
    fun getBrowserType(request: HttpServletRequest):String?{
        var type = "ie"
        val name:String = request.getHeader("USER-AGENT").toLowerCase()
        if (name.indexOf("msie") > 0) type = "ie"
        if (name.indexOf("firefox") > 0) type = "firefox"
        if (name.indexOf("chrome") > 0) type = "chrome"
        if (name.indexOf("opera") > 0) type = "opera"
        if (name.indexOf("gecko") > 0 && name.indexOf("rv:11")>0) type = "ie11"
        return type
    }

    /**
     * 得到浏览器类型
     */
    fun getBrowserName(request: HttpServletRequest):String?{
        val header:String? = request.getHeader("user-agent")
        val agent:UserAgent? = UserAgent.parseUserAgentString(header)
        val browser:Browser? = agent?.browser
        return browser?.name
    }

    /**
     * 得到浏览器版本号
     */
    fun getRrowserVersion(request: HttpServletRequest):String?{
        val header = request.getHeader("user-agent")
        val agent:UserAgent? = UserAgent.parseUserAgentString(header)
        val browser:Browser? = agent?.browser
        val version:Version? = browser?.getVersion(header)
        return version?.version
    }

    /**
     * 得到操作系统类型
     */
    fun getOsName(request: HttpServletRequest):String?{
        val header = request.getHeader("user-agent")
        val agent:UserAgent? = UserAgent.parseUserAgentString(header)
        val os:OperatingSystem? = agent?.operatingSystem
        return os?.name
    }

    /**
     * 生成验证码
     */
    fun createVerificationCode(request: HttpServletRequest,response: HttpServletResponse){

        try {
            val width:Int = 200
            val height:Int = 69

            val img:BufferedImage = BufferedImage(width,height,BufferedImage.TYPE_INT_RGB)

            val randomBuffer:String = VerifyCode.randomText(width,height,img)
            request.session.setAttribute("imgdode",randomBuffer)
            response.contentType = "image/png"
            val os:OutputStream = response.outputStream
            ImageIO.write(img,"png",os)
            os.flush()
            os.close()
        }catch (e:IOException){
            e.printStackTrace()
        }

    }

    /**
     * md5
     */

    /**
     * uuid
     */
    fun



    /**
     * 验证码
     */
    object VerifyCode{
        fun randomText(width:Int , height:Int ,verifyImg:BufferedImage):String{

            val gp:Graphics2D = verifyImg.graphics as Graphics2D
            gp.color = Color.white
            gp.fillRect(0,0,width, height)
            gp.font = Font("微软雅黑",Font.BOLD,40)

            val buffer:StringBuffer = StringBuffer()
            var x:Int = 10
            var ch = ""
            val random = Random()
            for (i in 0..4){
                gp.color = getRandomColor()
                val degree = random.nextInt() % 30
                val dot = random.nextInt(ConstantUtils.BASE_NUMBER.length)
                ch = ConstantUtils.BASE_NUMBER[dot] + ""
                buffer.append(ch)

                //旋转
                gp.rotate( degree * Math.PI / 180 , x + 0.0 , 45.0)
                gp.drawString(ch,x,45)

                //反向旋转
                gp.rotate( - degree * Math.PI / 180 , x + 0.0 , 45.0)

                x += 48
            }

            for (j in 0..6){

                gp.color = getRandomColor()
                gp.drawLine(random.nextInt(width) , random.nextInt(height),
                        random.nextInt(width) , random.nextInt(height))
            }
            for ( k in 0 .. 30){
                val x1 = random.nextInt(width)
                val y1 = random.nextInt(height)
                gp.color = getRandomColor()
                gp.fillRect(x1,y1,2,2)
            }

            return buffer.toString()

        }

        private fun getRandomColor():Color{
            val rand = Random()
            return Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256))
        }
    }
}