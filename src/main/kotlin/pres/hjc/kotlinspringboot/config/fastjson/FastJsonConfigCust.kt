package pres.hjc.kotlinspringboot.config.fastjson

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.support.config.FastJsonConfig
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/4/7
@time 14:02
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * WebMvcConfigurationSupport
 * SpringBoot内部提供专门处理用户自行添加的配置
 */
@Configuration
class FastJsonConfigCust:WebMvcConfigurer{

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        super.configureMessageConverters(converters)
        //创建fastJson消息转换器
        val fastCase = FastJsonHttpMessageConverter()
        //创建配置类
        val fastJsonConfig = FastJsonConfig()
        //修改过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
                /**
                 * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
                WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
                DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
                WriteMapNullValue：是否输出值为null的字段,默认为false。
                 */
        )
        fastCase.fastJsonConfig = fastJsonConfig
        //添加到视图转换器列表
        converters.add(fastCase)
    }

}