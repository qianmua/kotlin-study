//package pres.hjc.kotlinspringboot.config.security
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.builders.WebSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.web.firewall.HttpFirewall
//import org.springframework.security.web.firewall.StrictHttpFirewall
//import pres.hjc.kotlinspringboot.interceptor.security.UserServiceConfig
//
///**
//Created by IntelliJ IDEA.
//@author HJC
//@date 2020/3/22
//@time 18:22
//@version 1.0
//To change this template use File | Settings | File Templates.
// */
////@Configuration
////@EnableWebSecurity
//@Deprecated("暂不用")
//class MySecurityConfig : WebSecurityConfigurerAdapter() {
//
////    @Autowired
////    private lateinit var userService:UserServiceConfig
//    /**
//     * @Author: Galen
//     * @Description: HttpSecurity包含了原数据（主要是url）
//     * 通过withObjectPostProcessor将MyFilterInvocationSecurityMetadataSource和MyAccessDecisionManager注入进来
//     * 此url先被MyFilterInvocationSecurityMetadataSource处理，然后 丢给 MyAccessDecisionManager处理
//     * 如果不匹配，返回 MyAccessDeniedHandler
//     * @Date: 2019/3/27-17:41
//     * @Param: [http]
//     * @return: void
//     **/
//    override fun configure(http: HttpSecurity?) {
////        super.configure(http)
//        http!!.authorizeRequests()
//                /*.withObjectPostProcessor()*//*
////                .antMatchers("/", "/ft**").permitAll()
//                .and()
//                .formLogin().loginPage("/html/login.html")
//                *//*.loginProcessingUrl("/html/login.html")*//*
//                *//*.usernameParameter("username")
//                .passwordParameter("password")*//*
//                *//*.failureHandler()
//                .successHandler()*//*
//                .permitAll()
//                .and()
//                .logout().logoutUrl("/html/logout")
//                .permitAll()
//                .and().csrf().disable()*/
//                .antMatchers("/","/index").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/admin/login.html")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and().csrf().disable()
//    }
//
//    /**
//     * 配置密码加密格式
//     */
//    /*override fun configure(auth: AuthenticationManagerBuilder?) {
//        super.configure(auth)
//        auth!!.userDetailsService(userService).passwordEncoder(BCryptPasswordEncoder())
//    }*/
//
//    /**
//     * 白名单
//     * 配置资源拦截
//     */
//    override fun configure(web: WebSecurity?) {
////        super.configure(web)
//        web!!
//                .ignoring()
//                .antMatchers("/index.html","classpath:/static/**","/admin/login.html","/favicon.ico" , "/ft/**")
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security")
//    }
//
//    /**
//     * 配置地址栏不能识别 // 的情况
//     * @return
//     */
//    @Bean
//    fun allowUrlEncodedSlashHttpFirewall(): HttpFirewall {
//        val firewall = StrictHttpFirewall()
//        //此处可添加别的规则,目前只设置 允许双 //
//        firewall.setAllowUrlEncodedDoubleSlash(true)
//        return firewall
//    }
//
//
//
//}