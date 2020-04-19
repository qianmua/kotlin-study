//package pres.hjc.kotlinspringboot.interceptor.security
//
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.access.ConfigAttribute
//import org.springframework.security.access.SecurityConfig
//import org.springframework.security.web.FilterInvocation
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource
//import org.springframework.stereotype.Component
//import org.springframework.util.AntPathMatcher
//import pres.hjc.kotlinspringboot.service.impl.AuthServiceImpl
//
///**
//Created by IntelliJ IDEA.
//@author HJC
//@date 2020/3/22
//@time 19:00
//@version 1.0
//To change this template use File | Settings | File Templates.
// */
///**
// * @Description: FilterInvocationSecurityMetadataSource（权限资源过滤器接口）继承了 SecurityMetadataSource（权限资源接口）
// * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思。
// * 自定义权限资源过滤器，实现动态的权限验证
// * 它的主要责任就是当访问一个url时，返回这个url所需要的访问权限
// */
////@Component
//@Deprecated("null")
//class FilterSecurityMetadataSource:FilterInvocationSecurityMetadataSource {
//    @Autowired
//    private lateinit var authServiceImpl: AuthServiceImpl
//
//    private val antPathMatcher:AntPathMatcher = AntPathMatcher()
//
//    private val log = LoggerFactory.getLogger(FilterSecurityMetadataSource::class.java)
//
//    /**
//     * @Description: 此处方法如果做了实现，返回了定义的权限资源列表，
//     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确，
//     * 如果不需要校验，这里实现方法，方法体直接返回null即可。
//     * @Date: 2019/3/27-17:12
//     * @Param: []
//     * @return: java.util.Collection<org.springframework.security.access.ConfigAttribute>
//     **/
//    override fun getAllConfigAttributes(): MutableCollection<ConfigAttribute> {
//        TODO("Not yet implemented")
//    }
//
//    /**
//     * @Description: 方法返回类对象是否支持校验，
//     * web项目一般使用FilterInvocation来判断，或者直接返回true
//     * @Date: 2019/3/27-17:14
//     * @Param: [aClass]
//     * @return: boolean
//     **/
//    override fun supports(p0: Class<*>?): Boolean {
//        return true
//    }
//
//    override fun getAttributes(p0: Any?): MutableCollection<ConfigAttribute> {
//        val getRequestUrl = (p0 as FilterInvocation).requestUrl
//        val allMenu = authServiceImpl.queryAllMenu()
//        allMenu?.forEach {
//            if (antPathMatcher.match(it.url , getRequestUrl) && it.allRoles?.size  != null ){
//                val allRoles = it.allRoles
//                val size:Int = it.allRoles?.size ?: 0
//                val value = arrayOfNulls<String>(size)
////                val val2= Array<String>(size,{(it * 2 + 97) as String}) // lambda init
//                for (i in 0..size) value[i] = allRoles?.get(i)?.roles
//                log.info("当前访问路径是${getRequestUrl},这个url所需要的访问权限是${value}")
//                return SecurityConfig.createList(/*value*/)
//            }
//        }
//        /**
//         * @Description: 如果本方法返回null的话，意味着当前这个请求不需要任何角色就能访问
//         * 此处做逻辑控制，如果没有匹配上的，返回一个默认具体权限，防止漏缺资源配置
//         **/
//        log.info("当前访问路径是${getRequestUrl},这个url所需要的访问权限是{ROLE_LOGIN}")
//        return SecurityConfig.createList("ROLE_LOGIN")
//    }
//}