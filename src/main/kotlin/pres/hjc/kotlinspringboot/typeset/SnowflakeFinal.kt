package pres.hjc.kotlinspringboot.typeset

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/31
@time 22:48
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 雪花算法MD5
 */
class SnowflakeFinal {
    companion object{
        /**
         * 起始的时间戳
         */
        val START_TIMESTAMP = 1480166465631L
        /**
         * 每一部分占用的位数
         */
        val SEQUENCE_BIT = 12 //序列号占用的位数
        val MACHINE_BIT = 5 //机器标识占用的位数
        val DATA_CENTER_BIT = 5 //数据中心占用的位数
        /**
         * 每一部分的最大值
         */
        val MAX_SEQUENCE = -1L xor (-1L shl SEQUENCE_BIT)
        val MAX_MACHINE_NUM = -1L xor (-1L shl MACHINE_BIT)
        val MAX_DATA_CENTER_NUM = -1L xor (-1L shl DATA_CENTER_BIT)
        /**
         * 每一部分向左的位移
         */
        val MACHINE_LEFT = SEQUENCE_BIT
        val DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT
        val TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT
    }
    var dataCenterId:Long = -1
    var machineId:Long = -1
    var sequence :Long = -1
    var lastTimeStamp :Long = -1

    private fun getNextMill(): Long {
        var mill: Long = getNewTimeStamp()
        while (mill <= lastTimeStamp) {
            mill = getNewTimeStamp()
        }
        return mill
    }

    private fun getNewTimeStamp(): Long {
        return System.currentTimeMillis()
    }

    /**
     * 根据指定的数据中心ID和机器标志ID生成指定的序列号
     *
     * @param dataCenterId 数据中心ID
     * @param machineId    机器标志ID
     */
    open fun SnowFlakeShortUrl(dataCenterId: Long, machineId: Long): Unit {
        require(!(dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0)) { "DtaCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0！" }
        require(!(machineId > MAX_MACHINE_NUM || machineId < 0)) { "MachineId can't be greater than MAX_MACHINE_NUM or less than 0！" }
        this.dataCenterId = dataCenterId
        this.machineId = machineId
    }
    /**
     * 产生下一个ID
     *
     * @return
     */
    /*@Synchronized
    open fun nextId(): Long {
        var currTimeStamp = getNewTimeStamp()
        if (currTimeStamp < lastTimeStamp) {
            throw RuntimeException("Clock moved backwards.  Refusing to generate id")
        }
        if (currTimeStamp == lastTimeStamp) {
            //相同毫秒内，序列号自增
            sequence = sequence + 1 and MAX_SEQUENCE
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currTimeStamp = getNextMill()
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L
        }
        lastTimeStamp = currTimeStamp
        return currTimeStamp - START_TIMESTAMP shl TIMESTAMP_LEFT //时间戳部分
         or (dataCenterId shl DATA_CENTER_LEFT //数据中心部分
        ) or (machineId shl MACHINE_LEFT //机器标识部分
                ) or sequence //序列号部分
    }*/
}