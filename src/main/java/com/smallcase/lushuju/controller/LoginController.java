//package com.smallcase.lushuju.controller;
//
//import com.smallcase.lushuju.configure.WebSecurityConfig;
//import com.smallcase.lushuju.pojo.entity.UserEntity;
//import com.smallcase.lushuju.service.impl.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by huangds on 2017/10/24.
// */
//@Controller
//public class LoginController {
//
//    @Autowired
//    private LoginService loginService;
//
//    @GetMapping("/")
//    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY)String account, Model model){
//
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String login(){
//        return "register";
//    }
//
//    @PostMapping("/loginVerify")
//    public String loginVerify(String username,String password,HttpSession session){
//        UserEntity user = new UserEntity();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        boolean verify = loginService.verifyLogin(user);
//        if (verify) {
//            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
//            return "index";
//        } else {
//            return "redirect:/sell/register";
//        }
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session){
//        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
//        return "redirect:/sell/register";
//    }
//}
