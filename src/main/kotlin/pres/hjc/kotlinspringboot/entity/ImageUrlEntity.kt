package pres.hjc.kotlinspringboot.entity

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/2
@time 21:25
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * 图片地址 version（乐观锁用）
 */
data class ImageUrlEntity(var imgid:Long? ,
                          var name:String?,
                          var url:String? ,
                          var version:String?,
                          var auth:String?,
                          var status:Int?)
