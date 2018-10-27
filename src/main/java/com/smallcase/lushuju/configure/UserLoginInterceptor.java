package com.smallcase.lushuju.configure;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * package: com.smallcase.lushuju.configure
 * date: 2018/10/26 15:25
 *
 * @author smallcase
 * @since JDK 1.8
 */


/**
 * 登陆拦截器
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userId = request.getSession().getAttribute("userId");
//        System.out.println(12);

        return userId != null;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
