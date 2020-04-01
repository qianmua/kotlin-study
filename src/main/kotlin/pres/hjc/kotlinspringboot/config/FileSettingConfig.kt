package pres.hjc.kotlinspringboot.config

import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import org.springframework.util.unit.DataUnit
import javax.servlet.MultipartConfigElement

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/1
@time 18:30
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Configuration
class FileSettingConfig {
    @Bean
    fun multipart():MultipartConfigElement{
        val factory = MultipartConfigFactory()
        //单个文件最大
        factory.setMaxFileSize(DataSize.of(50,DataUnit.MEGABYTES)) //kb mb
        //总大小
        factory.setMaxRequestSize(DataSize.of(150,DataUnit.MEGABYTES))
        return factory.createMultipartConfig()
    }
}