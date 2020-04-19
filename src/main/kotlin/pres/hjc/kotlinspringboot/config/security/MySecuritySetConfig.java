//package pres.hjc.kotlinspringboot.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.firewall.HttpFirewall;
//import org.springframework.security.web.firewall.StrictHttpFirewall;
//
///**
// * Created by IntelliJ IDEA.
// *
// * @author HJC
// * @version 1.0
// * To change this template use File | Settings | File Templates.
// * @date 2020/4/19
// * @time 18:13
// */
////@Configuration
////@EnableWebSecurity
//    @Deprecated()
//public class MySecuritySetConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/","/ft/**","/pictures/**").permitAll()
//                .and()
//                .formLogin()
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();
//    }
//
//    /**
//     * 白名单
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
////        super.configure(web);
//        web.ignoring()
//                .antMatchers("/admin/**","/html/**","/pictures/**","/public/**")
//                .antMatchers("/index.html","/admin/login.html","/favicon.ico")
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/images/**", "/webjars/**", "/v2/api-docs", "/configuration/ui", "/configuration/security")
//        ;
//    }
//
//
//
//    /**
//     * 配置地址栏不能识别 // 的情况
//     * @return
//     */
//    @Bean
//    public HttpFirewall allowUrlEncodedSlashHttpFirewall(){
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowUrlEncodedDoubleSlash(true);
//        return firewall;
//    }
//
//}
