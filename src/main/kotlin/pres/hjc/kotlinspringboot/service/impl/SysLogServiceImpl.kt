package pres.hjc.kotlinspringboot.service.impl

import org.apache.juli.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pres.hjc.kotlinspringboot.entity.SysLogModel
import pres.hjc.kotlinspringboot.mapping.SysLogMapping
import pres.hjc.kotlinspringboot.service.SysLogService
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils
import java.text.SimpleDateFormat
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/2
@time 11:15
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Service
class SysLogServiceImpl:SysLogService {
    private val log = LogFactory.getLog(SysLogServiceImpl::class.java)
    @Autowired
    private lateinit var sysLogMapping: SysLogMapping
    override fun queryAll(): MutableList<SysLogModel>? = sysLogMapping.queryAll()

    override fun add(
            operation:String,
            request: HttpServletRequest): Int? {
        val ip = PublicToolsUtils.getIpAddress(request)
        val type = PublicToolsUtils.getBrowserName(request)
        val version = PublicToolsUtils.getRrowserVersion(request)
        val os = PublicToolsUtils.getOsName(request)
        val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Date())
        val sysLogModel = SysLogModel(-1,"test",operation,
                "test","test",ip,os,type,version,date)
        val line:Int? = sysLogMapping.add(sysLogModel)
        if (line == 0) log.info("add log value $line. save exception.") else log.info("add log value $line.")
        return line
    }
}