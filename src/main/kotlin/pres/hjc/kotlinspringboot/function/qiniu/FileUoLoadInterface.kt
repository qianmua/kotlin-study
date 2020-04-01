package pres.hjc.kotlinspringboot.function.qiniu

import org.springframework.web.multipart.MultipartFile

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/1
@time 16:24
@version 1.0
To change this template use File | Settings | File Templates.
 */
interface FileUoLoadInterface {
    fun upLoadFile(file:MultipartFile):String
}