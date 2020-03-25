package pres.hjc.kotlinspringboot.intercaptor.security

import org.springframework.security.access.AccessDecisionManager
import org.springframework.security.access.ConfigAttribute
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
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
 * @Author: Galen
 * @Date: 2019/3/27-16:59
 * @Description: Decision决定的意思。
 * 有了权限资源(MyFilterInvocationSecurityMetadataSource)，知道了当前访问的url需要的具体权限，接下来就是决策当前的访问是否能通过权限验证了
 * MyAccessDecisionManager 自定义权限决策管理器
 **/
@Component
class CustomAccessDecisionManager:AccessDecisionManager{

    /**
     * @Author: Galen
     * @Description: 取当前用户的权限与这次请求的这个url需要的权限作对比，决定是否放行
     * auth 包含了当前的用户信息，包括拥有的权限,即之前UserDetailsService登录时候存储的用户对象
     * object 就是FilterInvocation对象，可以得到request等web资源。
     * configAttributes 是本次访问需要的权限。即上一步的 MyFilterInvocationSecurityMetadataSource 中查询核对得到的权限列表
     * @Date: 2019/3/27-17:18
     * @Param: [auth, object, cas]
     * @return: void
     **/
    override fun decide(p0: Authentication?, p1: Any?, p2: MutableCollection<ConfigAttribute>) {
        val iterator:Iterator<ConfigAttribute> = p2.iterator()
        while (iterator.hasNext()){
            if (p0 == null) println("auth filed")
            val ca:ConfigAttribute = iterator.next()
            //当前权限
            val auth = ca.attribute
            if ("ROLE_LOGIN" == auth)  if (p0 is AnonymousAuthenticationToken )println("no info") else return


        }
    }

    override fun supports(p0: ConfigAttribute?): Boolean = true

    override fun supports(p0: Class<*>?): Boolean  = true

}