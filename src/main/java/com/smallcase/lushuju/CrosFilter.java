package com.smallcase.lushuju;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: com.smallcase.lushuju
 * Author: smallcase
 * Date: Created in 2018/8/9 21:23
 */
public class CrosFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String origin = request.getHeader("Origin");
        String header = request.getHeader("Access-Control-Request-Headers");
        String method = request.getMethod();
//        String contentType = request.getHeader("Content-Type");

//        response.addHeader("Access-Control-Allow-Headers", contentType);
        response.addHeader("Access-Control-Allow-Headers", header);
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Methods", method);
        response.addHeader("Access-Control-Allow-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        System.out.println(header + "\n" + origin + "\n" + method);

        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
}
