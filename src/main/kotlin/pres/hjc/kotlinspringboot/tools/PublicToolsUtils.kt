package pres.hjc.kotlinspringboot.tools

import com.qiniu.storage.Configuration
import com.qiniu.storage.Region
import com.qiniu.storage.UploadManager
import com.qiniu.util.Auth
import eu.bitwalker.useragentutils.Browser
import eu.bitwalker.useragentutils.OperatingSystem
import eu.bitwalker.useragentutils.UserAgent
import eu.bitwalker.useragentutils.Version
import org.springframework.web.multipart.MultipartFile
import pres.hjc.kotlinspringboot.config.quniu.FileUploadConfig.Qiniu
import pres.hjc.kotlinspringboot.function.qiniu.FileUoLoadInterface
import sun.misc.BASE64Encoder
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.OutputStream
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.net.InetAddress
import java.net.UnknownHostException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.experimental.and


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
        var ip:String? = request.getHeader("x-forwarded-for")
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
    fun getRowserVersion(request: HttpServletRequest):String?{
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

            val img = BufferedImage(width,height,BufferedImage.TYPE_INT_RGB)

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
    @Deprecated("使用MD5Two方法代替")
     fun md5(buffer: String): String? {
        var string: String? = null
        val hexDigist = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
        val md: MessageDigest
        try {
            md = MessageDigest.getInstance("MD5")
            md.update(buffer.toByteArray())
            val datas = md.digest() //16个字节的长整数
            val str = CharArray(2 * 16)
            var k = 0
            for (i in 0..15) {
                val b = datas[i]
                str[k++] = hexDigist[b.toInt() ushr 4 and 0xf] //高4位
                str[k++] = hexDigist[(b and 0xf).toInt()] //低4位
            }
            string = String(str)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return string
    }
    /**
     * md52
     */
    fun md5Two(semiFinishedProducts: String): String? {
        var lDigest: MessageDigest? = null
        try {
            lDigest = MessageDigest.getInstance("MD5")
            lDigest.update(semiFinishedProducts.toByteArray())
            val lHashInt = BigInteger(1, lDigest.digest())
            return java.lang.String.format("%1$032X", lHashInt)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * hash url encrpet
     */
    open fun encreptUrl(url: String): String? {
        try {
            val salt = "JKDSPnBKYJ2E7kEg9mYSteK4AXE8ywUB96y8gjDFhfy".toByteArray(charset("UTF-8"))
            val checkSalt = "C2NkXy3ECJn9AcMB976DnBKYJ2E7kEg9mYSte"
            val messageDigest = MessageDigest.getInstance("SHA-256")
            messageDigest.reset()
            messageDigest.update(salt)
            val bytes = url.toByteArray(charset("UTF-8"))
            val encryptUrl = messageDigest.digest(bytes)
            val semiFinishedProducts = BASE64Encoder().encode(encryptUrl)
            //加密url的长度我设置的6位. 加密的url取三位。剩下三位分别给静态盐1位和动态盐2位
            val urlKey: String = semiFinishedProducts.substring(0, 3)
            //位置可以在0-32位之间。这里可以选择位置。但是解密的时候就必须用同样的位置
            val staticSalt: String = md5Two(urlKey + checkSalt)!!.substring(4, 5)
            val dynaSalt: String = md5Two("" + UUID.randomUUID())!!.substring(5, 7)
            val encrypted = urlKey + staticSalt + dynaSalt
            //标记量。用来加强短链接检查.这里输出查看下
            val sig: String? = md5Two(encrypted)
            //            String domain = "www.baidu.com/";
//            String subDomain = "demo/";
            //把 sig ,encryptedUrl ,originalUrl,key。存到数据库
            return encrypted
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return null
    }

    fun getOriginUrl(encrpetUrl: String, sig: String): String? {
        var encrpetUrl = encrpetUrl
        encrpetUrl = encrpetUrl.substring(encrpetUrl.lastIndexOf("/") + 1)
        val key = encrpetUrl.substring(0, 3)
        val staticSalt = encrpetUrl.substring(3, 4)
        //和上面的检查盐一样
        val checkSalt = "C2NkXy3ECJn9AcMB976DnBKYJ2E7kEg9mYSte"
        //静态盐检查
        val correctStaticSalt: String = md5Two(key + checkSalt)!!.substring(4, 5)
        if (staticSalt != correctStaticSalt) {
            println(1)
            return "error"
        }
        val correctSig: String? = md5Two(encrpetUrl)
        if (sig != correctSig) {
            return "error"
        }
        //检查完毕。 没问题就通过key查询数据库。拿到原始url
        println(encrpetUrl)
        return correctSig
    }


    /**
     * uuid

     * 雪花算法
     *
     * Snowflake生成的是Long类型的ID，一个Long类型占8个字节，每个字节占8比特，也就是说一个Long类型占64个比特。

     * Snowflake ID组成结构：正数位（占1比特）+ 时间戳（占41比特）+ 机器ID（占5比特）+ 数据中心（占5比特）+ 自增值（占12比特），总共64比特组成的一个Long类型。

     * 第一个bit位（1bit）：Java中long的最高位是符号位代表正负，正数是0，负数是1，一般生成ID都为正数，所以默认为0。

     * 时间戳部分（41bit）：毫秒级的时间，不建议存当前时间戳，而是用（当前时间戳 - 固定开始时间戳）的差值，可以使产生的ID从更小的值开始；41位的时间戳可以使用69年，(1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69年

     * 工作机器id（10bit）：也被叫做workId，这个可以灵活配置，机房或者机器号组合都可以。

     * 序列号部分（12bit），自增值支持同一毫秒内同一个节点可以生成4096个ID

     */

    fun snowflakeUrl():String{
        return "1"
    }
    /**
     * uuid java
     */
    fun getUUID():String{
        return UUID.randomUUID().toString().replace("-","")
    }

    /**
     * 文件上传
     * 七牛
     */
     class UploadFileQiniu constructor(qiniu: Qiniu):FileUoLoadInterface{
        private var pro: Qiniu
        //构造一个带指定Region对象的配置类
        private var cfg = Configuration(Region.region0())
        private val um = UploadManager(cfg)

        init {
            this.pro = qiniu
        }

        override fun upLoadFile(file: MultipartFile): String {
            val auth = Auth.create(pro.accessKey,pro.secretKey)
            val token = auth.uploadToken(pro.bucket)
            try {
                //name
                val name = file.originalFilename
                val suffixName = name?.substring(name.lastIndexOf("."))
                val fileKey = getUUID() + suffixName
                val response = um.put(file.inputStream,fileKey,token,null,null)
                return pro.domain + fileKey
            }catch (e:IOException){
                e.printStackTrace()
            }
            return "error"
        }
    }

    /**
     * fastJson
     *
     * @return Any or Value
     */
    fun getJson():String?{
        return ""
    }


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