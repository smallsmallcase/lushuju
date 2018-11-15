package com.smallcase.lushuju.configure;

import com.alibaba.fastjson.JSONObject;
import com.smallcase.lushuju.pojo.enums.EnableStatusEnum;
import com.smallcase.lushuju.pojo.enums.RoleEnum;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * package: com.smallcase.lushuju.configure
 * date: 2018/11/12 15:42
 *权限管理拦截器
 * @author smallcase
 * @since JDK 1.8
 */

@Slf4j
public class UserOauthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer enableStatus = (Integer)request.getSession().getAttribute("enableStatus");

        /*
        权限判断，判断是否有操作的权限,判断enableStatus
         */
        if (enableStatus == null || !enableStatus.equals(EnableStatusEnum.YES.getEnableStatus())) {

            ResultVO resultVO = ResultVOUtil.noOauth("没有权限");
            String jsonString = JSONObject.toJSONString(resultVO);
            returnJson(response, jsonString);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
