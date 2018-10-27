package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.pojo.form.LoginParam;
import com.smallcase.lushuju.pojo.form.RegisterParam;
import com.smallcase.lushuju.service.AllService;
import com.smallcase.lushuju.service.UserInfoService;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    private UserInfoService service;
    @Autowired
    private AllService allService;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginParam param, HttpServletRequest request) {

        String username = param.getUserName().trim();
        String password = param.getPassWord().trim();
        UserEntity entity;

        try {
            entity = service.findByUsernameAndPassword(username, password);
        } catch (NoDataException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        log.info("登陆认证成功");
        //登陆成功就将UserId放入session
        request.getSession().setAttribute("userId", entity.getId());

        //从缓存中读取属于该用户的缓存信息
//        Set<String> users = redisTemplate.keys(username);
//        //读取缓存正确，继续录取
//        //只能缓存最近的一条信息，不继续处理就作废
//        if (users.size() == 1) {
//            /*cacheObj
//            {
//
//            personId:xxx,
//            requestUrl: xxx.xxx.xxx,
//            cacheValue:{x:11,y:22,....}
//
//            }
//             */
//            String jsonStr = redisTemplate.opsForValue().get(username);
//            JSONObject cacheObj = JSON.parseObject(jsonStr);
//            //读取缓存数据结束，清空缓存
//            redisTemplate.delete(users);
//            return RestfulResult.ok(ResultVOUtil.success(cacheObj));
//
//        }
//
//        //没有缓存，或者缓存信息不正确，清空该用户的开始新的数据录入
//        redisTemplate.delete(users);

        return RestfulResult.serviceErr(ResultVOUtil.loginSuccess(entity.getId()));

    }

    /**
     * 确认终止或完成一个病例的录入
     *
     * @return
     */
    @RequestMapping(value = "/finish", method = RequestMethod.GET)
    public ResponseEntity finish(HttpServletRequest request) {

        String currentUser = (String) request.getSession().getAttribute("currentUser");
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

    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    public ResponseEntity changePwd(@RequestParam String userName, @RequestParam String newPassword) {
        try {
            service.changepwd(userName, newPassword);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success("密码修改成功"));
    }



    /**
     * 按照用户，分页查找录入的病例
     * @param userId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/search/patients", method = RequestMethod.GET)
    public ResponseEntity searchPatients(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNum) {

        List<PersonInfo> personInfos;
        try {
            personInfos = service.listPersonInfoByUser(userId, pageSize, pageNum);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(personInfos));
    }

    @RequestMapping(value = "/search/patients_count", method = RequestMethod.GET)
    public ResponseEntity searchPatientsCount(@RequestParam(value = "userId") Integer userId) {
        Integer num;
        try {
            num = service.listPersonInfoNumByUserId(userId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(num));
    }

    @RequestMapping(value = "/search/patient/detail", method = RequestMethod.GET)
    public ResponseEntity searchPatientDetail(@RequestParam String personId) {
        JSONArray personInfo;
        try {
            personInfo = allService.findAllInfoByPersonId(personId);

        } catch (NoDataException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error("找不到该数据,personId不存在"));
        } catch (Exception e) {

            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(personInfo));

    }

}

