package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.pojo.form.LoginParam;
import com.smallcase.lushuju.pojo.form.RegisterParam;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.repository.UserEntityRepository;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.Map;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/2 17:03
 */

@RestController
@Slf4j
public class IndexController {

    @Autowired
    private UserEntityRepository repository;

    /**
     * index页面
     */
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


//    /**
//     * 登陆页面
//     *
//     * @param
//     * @return
//     */
//
//    @RequestMapping(value="/login",method=RequestMethod.GET)
//    public String login(){
//        return "login";
//    }



    @PostMapping(value = "/login")
    public ResultVO login(@RequestBody LoginParam param) {
        String username = param.getUserName().trim();
        String password = param.getPassWord().trim();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.error("登陆认证失败");
            return ResultVOUtil.error("登陆出错");
        }
        log.info("登陆认证成功");
        return ResultVOUtil.success();


    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
//    @RequestMapping(value="/login",method=RequestMethod.POST)
//    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
//        System.out.println("HomeController.login()");
//        // 登录失败从request中获取shiro处理的异常信息。
//        // shiroLoginFailure:就是shiro异常类的全类名.
//        String exception = (String) request.getAttribute("shiroLoginFailure");
//
//        System.out.println("exception=" + exception);
//        String msg = "";
//        if (exception != null) {
//            if (UnknownAccountException.class.getName().equals(exception)) {
//                System.out.println("UnknownAccountException -- > 账号不存在：");
//                msg = "UnknownAccountException -- > 账号不存在：";
//            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
//                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
//                msg = "IncorrectCredentialsException -- > 密码不正确：";
//            } else if ("kaptchaValidateFailed".equals(exception)) {
//                System.out.println("kaptchaValidateFailed -- > 验证码错误");
//                msg = "kaptchaValidateFailed -- > 验证码错误";
//            } else {
//                msg = "else >> "+exception;
//                System.out.println("else -- >" + exception);
//            }
//        }
//        map.put("msg", msg);
//        // 此方法不处理登录成功,由shiro进行处理.
//        return "/index";
//    }


    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping(name = "/register")
    public String register() {

        return "register";
    }

    /**
     * 注册方法
     */

    @RequestMapping(name = "/addregister", method = RequestMethod.POST)
    public ResultVO register(@RequestBody RegisterParam param) {
        String username = param.getUserName().trim();
        String password = param.getPassWord().trim();
        String password2 = param.getPassWord2().trim();
        if (password.equals(password2)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            repository.save(userEntity);
            return ResultVOUtil.success(userEntity);
        } else {
            return ResultVOUtil.error("注册失败");
        }

    }
}
