package pres.hjc.kotlinspringboot.controller.image

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile
import pres.hjc.kotlinspringboot.config.quniu.FileUploadConfig
import pres.hjc.kotlinspringboot.tools.PublicToolsUtils

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/1
@time 18:42
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/foc")
class FileOperationController {
    @Autowired
    private lateinit var fileUploadConfig:FileUploadConfig

    @PostMapping("/uimg")
    fun fileUploadQiniu(file:MultipartFile):String{
        val upFile = PublicToolsUtils.UploadFileQiniu(fileUploadConfig.getQiniu())
        return upFile.upLoadFile(file)
    }
}