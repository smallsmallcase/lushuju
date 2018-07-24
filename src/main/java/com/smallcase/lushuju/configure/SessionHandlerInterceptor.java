//package com.smallcase.lushuju.configure;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Package: com.smallcase.lushuju.configure
// * Author: smallcase
// * Date: Created in 2018/7/2 22:31
// */
//public class SessionHandlerInterceptor implements HandlerInterceptor{
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        HttpSession session = httpServletRequest.getSession();
//        if (session.getAttribute("username") != null) {
//            return true;
//        }
//
//        //否则跳转到登陆页面
//        String url = "sell/register";
//        httpServletResponse.sendRedirect(url);
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
