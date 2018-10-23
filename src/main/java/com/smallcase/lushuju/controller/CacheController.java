package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/10/20 16:34
 */


@Controller
public class CacheController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 进行缓存
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/cache", method = RequestMethod.POST)
    public ResponseEntity cache(HttpServletRequest request, @RequestBody JSONObject cacheData) {
        try {

        /*cacheObj
            {

            personId:xxx,
            contextPath: xxx.xxx.xxx,
            cacheData:{x:11,y:22,....}

            }
             */
            //TODO contextPath
            String personId = (String) request.getSession().getAttribute("personId");

            String currentUser = (String) request.getSession().getAttribute("currentUser");


            if (cacheData != null && currentUser != null) {

                HashMap<String, Object> cacheObj = new HashMap<>();
//                cacheObj.put("requestUrl", requestUrl);
                cacheObj.put("cacheValue", cacheData);

                //如果缓存的是录入personInfo时候的信息，personId不存在，personId只能在提交personInfo信息后才能从数据库中返回回来d
                if (personId != null) {
                    cacheObj.put("personId", personId);
                }

                String jsonStr = JSON.toJSONString(cacheObj);

                redisTemplate.opsForValue().set(currentUser, jsonStr);

                return RestfulResult.ok(ResultVOUtil.success("缓存成功"));
            } else {
                return RestfulResult.serviceErr(ResultVOUtil.error("缓存失败"));
            }
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error("缓存失败"));

        }


    }
}
