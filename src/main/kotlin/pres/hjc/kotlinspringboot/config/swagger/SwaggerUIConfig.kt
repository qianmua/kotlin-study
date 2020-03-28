package pres.hjc.kotlinspringboot.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pres.hjc.kotlinspringboot.tools.ConstantUtils
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/28
@time 14:50
@version 1.0
To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableSwagger2
class SwaggerUIConfig {

    @Bean
    fun createRestApi():Docket{
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(ConstantUtils.SWAGGER_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo():ApiInfo{
        return ApiInfoBuilder()
                .title("任务发布管理系统")
                .description("API接口文档")
                .version(ConstantUtils.SWAGGER_VERSION)
                .termsOfServiceUrl("nolimithjc.top:8080")
                .build()
    }

}