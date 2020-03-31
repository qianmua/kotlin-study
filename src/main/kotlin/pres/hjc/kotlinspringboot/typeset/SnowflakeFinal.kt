package pres.hjc.kotlinspringboot.typeset

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/31
@time 22:48
@version 1.0
To change this template use File | Settings | File Templates.
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
    }
}