package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.pojo.enums.EnableStatusEnum;
import com.smallcase.lushuju.pojo.enums.RoleEnum;
import com.smallcase.lushuju.pojo.form.LoginParam;
import com.smallcase.lushuju.pojo.form.RegisterParam;
import com.smallcase.lushuju.pojo.view.CheckStatusVO;
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
        request.getSession().setAttribute("roleId", entity.getRoleId());
        request.getSession().setAttribute("enableStatus", entity.getEnableStatus());

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


        int num;
        try {
            num = service.listPersonInfoNumByUserId(entity.getId());
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error("获取录入病例数出错"));
        }

        return RestfulResult.serviceErr(ResultVOUtil.loginSuccess(entity.getId(), num, entity.getRoleId()));

    }


    @RequestMapping(value = "/login_check", method = RequestMethod.GET)
    public ResponseEntity check(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            return RestfulResult.serviceErr(ResultVOUtil.error("不在登陆状态"));

        }

        UserEntity userEntity = service.findById(userId);
        int num = service.listPersonInfoNumByUserId(userId);
        CheckStatusVO checkStatusVO = new CheckStatusVO();
        checkStatusVO.setUserId(userId);
        checkStatusVO.setUserName(userEntity.getUsername());
        checkStatusVO.setRecordedNumber(num);

        return RestfulResult.ok(ResultVOUtil.success(checkStatusVO));
    }
    /**
     * 确认终止或完成一个病例的录入
     *
     * @return
     */
//    @RequestMapping(value = "/finish", method = RequestMethod.GET)
//    public ResponseEntity finish(HttpServletRequest request) {
//
//        String currentUser = (String) request.getSession().getAttribute("currentUser");
//        if (currentUser != null) {
//
//            //清除redis缓存中的数据
//            Set<String> keys = redisTemplate.keys(currentUser);
//            if (keys.size() > 0) {
//                redisTemplate.delete(keys);
//            }
//
//            //清空session中病人的信息
//            request.getSession().removeAttribute("personId");
//
//        }
//        return null;
//    }


    /**
     * 账户注册
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

    /**
     * 修改密码
     * @param userName
     * @param newPassword
     * @return
     */
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
     * 管理员更改用户可执行状态
     * @param request
     * @param targetStatus
     * @param userId
     * @return
     */
    @RequestMapping(value = "/changestatus", method = RequestMethod.GET)
    public ResponseEntity changeStatus(HttpServletRequest request, @RequestParam Integer targetStatus, @RequestParam Integer userId) {
        Integer roleId = (Integer)request.getSession().getAttribute("roleId");

        //验证是不是管理员
        if (roleId == null || !roleId.equals(RoleEnum.ADMIN.getRoleId())) {
            System.out.println(RoleEnum.ADMIN.getRoleId());
            System.out.println(RoleEnum.ADMIN.getRoleId().equals(roleId));
            return RestfulResult.serviceErr(ResultVOUtil.error("没有修改权限"));
        }

        if (targetStatus.equals(EnableStatusEnum.YES.getEnableStatus())) {
            try {
                service.changeStatus(targetStatus, userId);
            } catch (RuntimeException e) {
                return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
            }
        } else if (targetStatus.equals(EnableStatusEnum.NO.getEnableStatus())) {
            try {
                service.changeStatus(targetStatus, userId);
            } catch (RuntimeException e) {
                return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
            }
        } else {
            return RestfulResult.serviceErr(ResultVOUtil.error("targetStatus传错"));
        }

        return RestfulResult.ok(ResultVOUtil.success("修改状态成功"));

    }


    /**
     * 按照用户，分页查找录入的病例
     *
     * @param userId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/search/patients", method = RequestMethod.GET)
    public ResponseEntity searchPatients(@RequestParam(value = "userId") Integer userId,
                                         @RequestParam(defaultValue = "10") int pageSize,
                                         @RequestParam(defaultValue = "1") int pageNum, HttpServletRequest request) {
        Integer userId1 = (Integer) request.getSession().getAttribute("userId");
        if (!userId.equals(userId1)) {
            return RestfulResult.serviceErr(ResultVOUtil.error("传入的userId和登陆的用户不一致"));
        }

        List<PersonInfo> personInfos;
        try {
            personInfos = service.listPersonInfoByUser(userId, pageSize, pageNum);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(personInfos));
    }


    /**
     * 根据userId查询用户录入了多少病例
     * @param userId
     * @return
     */
    @RequestMapping(value = "/search/patients_count", method = RequestMethod.GET)
    public ResponseEntity searchPatientsCount(@RequestParam(value = "userId") Integer userId,
                                              HttpServletRequest request) {
        Integer userId1 = (Integer) request.getSession().getAttribute("userId");
        if (!userId.equals(userId1)) {
            return RestfulResult.serviceErr(ResultVOUtil.error("传入的userId和登陆的用户不一致"));
        }

        Integer num;
        UserEntity userEntity;

        try {
            userEntity = service.findById(userId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        try {
            num = service.listPersonInfoNumByUserId(userId);

        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        CheckStatusVO checkStatusVO = new CheckStatusVO();
        checkStatusVO.setUserId(userId);
        checkStatusVO.setUserName(userEntity.getUsername());
        checkStatusVO.setRecordedNumber(num);

        return RestfulResult.ok(ResultVOUtil.success(checkStatusVO));
    }


    /**
     * 根据personId查询该病人的详细信息
     * @param personId
     * @return
     */
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

