package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smallcase.lushuju.service.ExternalApplicaitionServlce;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * package: com.smallcase.lushuju.controller
 * date: 2018/11/18 21:23
 *
 * @author smallcase
 * @since JDK 1.8
 */

@RestController
public class ExternalApplicationController {

    private final ExternalApplicaitionServlce servlce;

    @Autowired
    public ExternalApplicationController(ExternalApplicaitionServlce servlce) {
        this.servlce = servlce;
    }

    /**
     * 执行外部程序
     * @param filePath
     * @param request
     * @return
     */
    @RequestMapping(value = "/run/external/Application", method = RequestMethod.GET)
    public ResponseEntity runApplication(@RequestParam String filePath, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        String appPath = null;
//        for (Cookie cookie : cookies) {
//            if (cookie.getName() == "appPath") {
//                appPath = cookie.getValue();
//            }
//        }
//
//        if (appPath == null) {
//            return RestfulResult.serviceErr(ResultVOUtil.error("程序路径找不到，或设置路径时间过期，请重新指定路径"));
//        }

        String appPath = "D:\\program\\dolphin\\DolphinViewer.exe";

        try {
            servlce.runApplication(appPath,filePath);
        } catch (IOException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success("外部程序正在运行"));

    }


    /**
     * 将外部程序完整路径添加到cookie中
     *
     * @param appPath
     * @return
     */
//    @RequestMapping(value = "/run/external/addAppPath", method = RequestMethod.POST)
//    public ResponseEntity addAppPath(@RequestBody String appPath, HttpServletResponse response) {
//
//        Cookie cookie;
//        try {
//            cookie = new Cookie("appPath", appPath);
//
//        } catch (IllegalArgumentException e) {
//            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
//        }
//
//        //30天
//        int maxAge = 3600*24*30;
//        cookie.setMaxAge(maxAge);
//        response.addCookie(cookie);
//        return RestfulResult.ok(ResultVOUtil.success("添加cookie成功"));
//
//    }
}
