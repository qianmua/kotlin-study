package pres.hjc.kotlinspringboot.sendtime

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/5
@time 23:42
@version 1.0
To change this template use File | Settings | File Templates.
 */
//@Component
class ExportTimeLog {

    private var log = LoggerFactory.getLogger(ExportTimeLog::class.java)

//    @Scheduled( cron = "0 0 12,23 * * ?")
    fun mainInfo(){
        //查询
        log.info("刷新规则。")
    }

    /**
     * "0 0 12 * * ?" 每天中午12点触发
    "0 15 10 ? * *" 每天上午10:15触发
    "0 15 10 * * ?" 每天上午10:15触发
    "0 15 10 * * ? *" 每天上午10:15触发
    "0 15 10 * * ? 2005" 2005年的每天上午10:15触发
    "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
    "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
    "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
    "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
    "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
    "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
    "0 15 10 15 * ?" 每月15日上午10:15触发
    "0 15 10 L * ?" 每月最后一日的上午10:15触发
    "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
    "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
    "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
    每隔5秒执行一次：*。/5 * * * * ?
    每隔1分钟执行一次：0 *。/1 * * * ?
    每天23点执行一次：0 0 23 * * ?
    每天凌晨1点执行一次：0 0 1 * * ?
    每月1号凌晨1点执行一次：0 0 1 1 * ?
    每月最后一天23点执行一次：0 0 23 L * ?
    每周星期天凌晨1点实行一次：0 0 1 ? * L
     */
}