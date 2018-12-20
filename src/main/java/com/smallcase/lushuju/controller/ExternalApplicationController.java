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
@RequestMapping(path = "/external")
@Deprecated
public class ExternalApplicationController {

    private final ExternalApplicaitionServlce servlce;

    @Autowired
    public ExternalApplicationController(ExternalApplicaitionServlce servlce) {
        this.servlce = servlce;
    }


    /**
     * 添加图片包子路径
     * @param personId
     * @param json
     * @return
     */
    @PostMapping(value = "/addFilePath")
    public ResponseEntity addFilePath(@RequestParam String personId, @RequestBody JSONObject json) {
        try {
            String filePath = (String)json.get("fileSuffixPath");
            filePath = filePath.replace("\\", "/");
            servlce.addFilePath(personId, filePath);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error("添加文件子路径出错"));
        }
        return RestfulResult.ok(ResultVOUtil.success("添加文件子路径成功"));

    }


    /**
     * 根据personId，获取图片文件包的子路径
     * @param personId
     * @return
     */
    @GetMapping(value = "get/suffix")
    public ResponseEntity getSuffixPath(@RequestParam String personId) {
        String filePath;
        try {
            filePath = servlce.readFilePath(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));

        }
        return RestfulResult.ok(ResultVOUtil.success(filePath));
    }
    /**
     * 执行外部程序
     *
     * @param pathPackage
     * @return
     */
    @RequestMapping(value = "/run/Application", method = RequestMethod.POST)
    public ResponseEntity runApplication(@RequestBody JSONObject pathPackage) {

//        Cookie[] cookies = request.getCookies();
//        String appPath = null;
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("appPath")) {
//                appPath = cookie.getValue();
//            }
//        }

        if (pathPackage.get("appPath") == null) {
            return RestfulResult.serviceErr(ResultVOUtil.error("程序路径找不到，或设置路径时间过期，请重新指定路径"));
        }

//        String appPath = "D:\\program\\dolphin\\DolphinViewer.exe";

        String appPath = (String) pathPackage.get("appPath");
        String filePath = (String)pathPackage.get("fileFullPath");
//        try {
//            filePath = servlce.readFilePath(personId);
//        } catch (Exception e) {
//            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
//
//        }
        try {
            servlce.runApplication(appPath, filePath);
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
//    @RequestMapping(value = "/addAppPath", method = RequestMethod.POST)
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
//        return RestfulResult.ok(ResultVOUtil.success("添加程序的路径到cookie成功"));

//    }
}
