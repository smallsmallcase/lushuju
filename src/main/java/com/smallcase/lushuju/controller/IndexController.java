package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.pojo.form.LoginParam;
import com.smallcase.lushuju.pojo.form.RegisterParam;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.repository.UserEntityRepository;
import com.smallcase.lushuju.service.UserEntityService;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private UserEntityService service;




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


    /**
     * 注册方法
     */

    @RequestMapping(name = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody RegisterParam param) {
        String username = param.getUserName().trim();
        String password = param.getPassWord().trim();
        String password2 = param.getPassWord2().trim();
        if (password.equals(password2)) {
            try {
                UserEntity result = service.register(username, password);
                return RestfulResult.ok(ResultVOUtil.success(result));
            } catch (Exception e) {
                return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
            }
        } else {
            return RestfulResult.serviceErr(ResultVOUtil.error("两次输入的密码不一致"));
        }

    }
}
