package pres.hjc.kotlinspringboot.intercaptor.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
import org.springframework.stereotype.Component

/**
Created by IntelliJ IDEA.
@author HJC
@date 2020/3/22
@time 19:00
@version 1.0
To change this template use File | Settings | File Templates.
 */
/**
 * @Description: FilterInvocationSecurityMetadataSource（权限资源过滤器接口）继承了 SecurityMetadataSource（权限资源接口）
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
 * 自定义权限资源过滤器，实现动态的权限验证
 * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
 */
@Component
class FilterSecurityMetadataSource:FilterInvocationSecurityMetadataSource {
    @Autowired


    override fun getAllConfigAttributes(): MutableCollection<ConfigAttribute> {
        TODO("Not yet implemented")
    }

    override fun supports(p0: Class<*>?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAttributes(p0: Any?): MutableCollection<ConfigAttribute> {
        TODO("Not yet implemented")
    }

}