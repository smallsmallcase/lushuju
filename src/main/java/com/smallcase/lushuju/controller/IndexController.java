package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import java.util.Set;

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
    @Autowired
    private StringRedisTemplate redisTemplate;


    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginParam param, HttpServletRequest request) {

        String username = param.getUserName().trim();
        String password = param.getPassWord().trim();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            log.error("登陆认证失败");
            return RestfulResult.serviceErr(ResultVOUtil.error("登陆出错"));
        }
        log.info("登陆认证成功");
        //登陆成功就将将用户名放入session中
        request.getSession().setAttribute("currentUser", username);

        //从缓存中读取属于该用户的缓存信息
        Set<String> users = redisTemplate.keys(username);

        //读取缓存正确，继续录取
        //只能缓存最近的一条信息，不继续处理就作废
        if (users.size() == 1) {
            /*cacheObj
            {

            personId:xxx,
            contextPath: xxx.xxx.xxx,
            cacheValue:{x:11,y:22,....}

            }
             */
            String jsonStr = redisTemplate.opsForValue().get(username);
            JSONObject cacheObj = JSON.parseObject(jsonStr);
            //读取缓存数据结束，清空缓存
            redisTemplate.delete(users);
            return RestfulResult.ok(ResultVOUtil.success(cacheObj));

        }

        //没有缓存，或者缓存信息不正确，清空该用户的开始新的数据录入
        redisTemplate.delete(users);
        return RestfulResult.serviceErr(ResultVOUtil.success());

    }

    /**
     * 确认终止或完成一个病例的录入
     *
     * @return
     */
    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public ResponseEntity finish(HttpServletRequest request) {

        String currentUser = (String)request.getSession().getAttribute("currentUser");
        if (currentUser != null) {

            //清除redis缓存中的数据
            Set<String> keys = redisTemplate.keys(currentUser);
            if (keys.size() > 0) {
                redisTemplate.delete(keys);
            }

            //清空session中病人的信息
            request.getSession().removeAttribute("personId");

        }
        return null;
    }


    /**
     * 注册方法
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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
