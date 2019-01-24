package com.zj.zchat.shiro;

import com.zj.zchat.pojo.User;
import com.zj.zchat.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    /*获取授权信息*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = userService.getUserByUsername((String) principalCollection.getPrimaryPrincipal());
        SecurityUtils.getSubject().getSession().setAttribute(user.getUsername(),SecurityUtils.getSubject().getPrincipals());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /*获取身份验证信息*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        User user=userService.getUserByUsername(token.getUsername());
        if (user!=null){
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user",user);
            return new SimpleAuthenticationInfo(username,user.getPassword(),getName());
        }
        return null;
    }
}
