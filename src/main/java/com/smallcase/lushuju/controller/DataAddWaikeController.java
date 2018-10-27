package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/5 17:39
 */

@RestController
@RequestMapping(path = "/waike/add")
public class DataAddWaikeController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private HealthInfoService healthInfoService;

    @Autowired
    private SpecialityCheckupService specialityCheckupService;

    @Autowired
    private LaboratoryCheckupService laboraryCheckupService;


    @PostMapping(value = "/personInfo")
    public ResponseEntity addPersonInfo(@RequestBody PersonInfo personInfo, HttpServletRequest request) {

        PersonInfo result;


        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            return RestfulResult.serviceErr(ResultVOUtil.error("session中数据丢失"));
        }

        //将用户的信息放入病人的主表中
        personInfo.setUserId(userId);

        try {
            result = personInfoService.save(personInfo);

        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error("录入personINfo出错，可能缺少字段"));
        }
        String personId = result.getPersonId();
        //录入personInfo信息，需要将id存入session中
        request.getSession().setAttribute("personId", personId);
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/medicalHistory")
    public ResponseEntity addMedicalHistory(@PathVariable("id") String personId,@RequestBody MedicalHistory medicalHistory) {
        medicalHistory.setPersonId(personId);

        MedicalHistory result;
        try {
             result= medicalHistoryService.save(medicalHistory);
        } catch (MyException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.serviceErr(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/healthInfo")
    public ResponseEntity addHealthInfo(@PathVariable("id") String personId, @RequestBody HealthInfo healthInfo) {
        healthInfo.setPersonId(personId);
        HealthInfo result;


        try {
            result = healthInfoService.save(healthInfo);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/specialityCheckup")
    public ResponseEntity addSpecialityCheckup(@PathVariable("id") String personId, @RequestBody SpecialityCheckup specialityCheckup) {
        specialityCheckup.setPersonId(personId);

        SpecialityCheckup result;
        try {
            result = specialityCheckupService.save(specialityCheckup);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/laboratoryCheckup")
    public ResponseEntity addLaboraryCheckup(@PathVariable("id") String personId,@RequestBody LaboratoryCheckup laboraryCheckup, HttpSession session) {
        laboraryCheckup.setPersonId(personId);
        LaboratoryCheckup result;
        try {
             result = laboraryCheckupService.save(laboraryCheckup);
        } catch (MyException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }
}
