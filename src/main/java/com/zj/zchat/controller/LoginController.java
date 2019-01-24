package com.zj.zchat.controller;

import com.zj.zchat.service.FriendsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private FriendsService friendsService;

    @RequestMapping("/loginpage")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginjudege")
    public String judgeLogin(String username, String password, boolean rememberme, Model model) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rememberme);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            return "账号不存在";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }
        List<String> list= friendsService.fgetByUsername(username);
        model.addAttribute("username",username);
        model.addAttribute("friends",list);
        return "/index";
    }

    @RequestMapping("/indexpage")
    public String index(){
        return "/index";
    }
}
