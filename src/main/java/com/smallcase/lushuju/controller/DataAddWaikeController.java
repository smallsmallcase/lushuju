package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/5 17:39
 */

@RestController
@RequestMapping(path = "/waike/add")
public class DataAddWaikeController {


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


    /**
     * 根据personId，插入,修改，删除该病人的疾病大类信息
     * @param array
     * @param personId
     * @return
     */
    @PostMapping(value = "/personInfo/execBigClass")
    public ResponseEntity addBigClass(@RequestBody JSONArray array, @RequestParam String personId) {

        /**检查该病人的疾病大类是否存在，存在的话就先删除再修改，或直接删除*/
        boolean existed = personInfoService.checkBigClassExisted(personId);
        if (existed) {
            try {
                personInfoService.deleteAllBigClassByPersonId(personId);
            } catch (MyException e) {
                return RestfulResult.serviceErr(ResultVOUtil.error("删除或覆盖该病人疾病大类信息时出错" +
                        "原因是：" + "\n" + e.getMessage()));
            }
        }

        List<ClassToPerson> list = new ArrayList<>();
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            ClassToPerson classToPerson = new ClassToPerson();
            classToPerson.setClassId((int) iterator.next());
            classToPerson.setPersonId(personId);
            list.add(classToPerson);
        }
        //转换数据
//        try {
////            list = JSONObject.parseArray(array.toJSONString(), ArrayList.class);
//        } catch (Exception e) {
//            return RestfulResult.serviceErr(ResultVOUtil.error("疾病数组信息转换失败"));
//        }

        //保存数据
        try {
            personInfoService.insertBigClass(list);
        } catch (MyException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success("保存该病人疾病大类信息成功"));

    }
    @PostMapping(value = "/personInfo")
    public ResponseEntity addPersonInfo(@RequestBody PersonInfo personInfo, HttpServletRequest request) {

        PersonInfo result;


        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            return RestfulResult.serviceErr(ResultVOUtil.error("session中数据丢失"));
        }

        //将用户的信息放入病人的主表中
        personInfo.setUserId(userId);
        boolean existed = personInfoService.checkExisted(personInfo.getPatientId());

        try {
            if (existed) {
                String personId = personInfoService.findPersonIdByPatientId(personInfo.getPatientId());
                result = personInfoService.edit(personInfo, personId);
            } else {
                result = personInfoService.save(personInfo);
            }

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
        boolean existed = medicalHistoryService.checkExisted(personId);
        try {
            if (existed) {
                result = medicalHistoryService.edit(medicalHistory, personId);
            } else {
                result = medicalHistoryService.save(medicalHistory);
            }
        } catch (MyException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.serviceErr(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/healthInfo")
    public ResponseEntity addHealthInfo(@PathVariable("id") String personId, @RequestBody HealthInfo healthInfo) {
        healthInfo.setPersonId(personId);
        HealthInfo result;


        boolean existed = healthInfoService.checkExisted(personId);
        try {
            if (existed) {
                result = healthInfoService.edit(healthInfo, personId);
            } else {
                result = healthInfoService.save(healthInfo);
            }
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

        boolean existed = specialityCheckupService.checkExisted(personId);
        try {
            if (existed) result = specialityCheckupService.edit(specialityCheckup, personId);
            else {
                result = specialityCheckupService.save(specialityCheckup);
            }
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

        boolean existed = laboraryCheckupService.checkExisted(personId);
        try {

            if (existed) result = laboraryCheckupService.edit(laboraryCheckup, personId);
            else result = laboraryCheckupService.save(laboraryCheckup);

        } catch (MyException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }


}
