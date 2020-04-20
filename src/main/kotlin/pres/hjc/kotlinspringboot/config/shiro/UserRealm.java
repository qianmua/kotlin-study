package pres.hjc.kotlinspringboot.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import pres.hjc.kotlinspringboot.entity.UserModel;
import pres.hjc.kotlinspringboot.service.impl.UserServiceImpl;

/**
 * Created by IntelliJ IDEA.
 *
 * @author HJC
 * @version 1.0
 * To change this template use File | Settings | File Templates.
 * @date 2020/4/19
 * @time 20:34
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("授权 ----------<");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("post2");

        //得到当前用户对象，
        Subject subject = SecurityUtils.getSubject();
        //user
        UserModel userModel = (UserModel) subject.getPrincipal();
        info.addStringPermission(userModel.getAdmin().toString());
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("认证 -------------->");
        //用户名密码呗~
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserModel userModel = userService.login(token.getUsername(), String.valueOf(token.getPassword()));
        System.out.println(token.getPassword());
        if (userModel == null){
            //抛出用户名错误异常
            return null;
        }

        //密码认证
        return new SimpleAuthenticationInfo(userModel,userModel.getPassword(),"");
        //得到当前请求
//        return null;
    }
}
