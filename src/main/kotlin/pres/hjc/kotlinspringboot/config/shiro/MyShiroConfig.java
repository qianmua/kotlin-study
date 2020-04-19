package pres.hjc.kotlinspringboot.config.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/19
 * @time 20:30
 */
@Configuration
public class MyShiroConfig {

    /*shiro filter factory bean
    * default web security manager
    * realm*/

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加内置过滤器
        /*anon 无需认证
        * authc 必须认证
        * user 必须拥有 记住我功能
        * perms 拥有对某个资源的权限
        * roles 拥有某个角色权限*/

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/","anon");
        filterMap.put("/ft/*","anon");
        filterMap.put("/login","anon");
        filterMap.put("/admins/*","authc");
        filterMap.put("/admins/login","anon");
        filterMap.put("/admins/login.html","anon");
        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("/admins/login.html");
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(
            @Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //关联realm
        defaultWebSecurityManager.setRealm(userRealm);

        return defaultWebSecurityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
