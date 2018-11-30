package com.smallcase.lushuju.controller;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.view.PersonIdsArray;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/18 21:37
 */

@Slf4j
@RestController
@RequestMapping(path = "/waike")
public class DataFindAndEditWaikeController {
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


    @GetMapping(value = "/deleteOne")
    public ResponseEntity deleteOnePersonByPersonId(@RequestParam String personId) {

        try {
            personInfoService.deleteOnePersonByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error("删除此病人出错，可能personId不一致" + e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success("删除成功"));

    }
    @GetMapping(value = "/search/classIds")
    public ResponseEntity findClassIdsByPersonId(@RequestParam String personId) {
        List<ClassToPerson> list = personInfoService.findClassToPersonByPersonId(personId);
        if (list.size() == 0) {
            return RestfulResult.serviceErr(ResultVOUtil.error("找不到对应的疾病ID"));
        }

        JSONArray jsonArray = new JSONArray();
        Iterator<ClassToPerson> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.add(iterator.next().getClassId());
        }
        return RestfulResult.ok(ResultVOUtil.success(jsonArray));
    }

    /**
     * 分页，根据疾病ID查询personId数组
     * @param classId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/search/personIds")
    public ResponseEntity findpersonIdsByClassId(@RequestParam Integer classId,
                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                 @RequestParam(defaultValue = "1") int pageNum) {

        List<ClassToPerson> list = personInfoService.findClassToPersonByClassId(classId,pageSize,pageNum);
        if (list.size() == 0) {
            return RestfulResult.serviceErr(ResultVOUtil.error("找不到对应的personId，个数为0"));
        }

        JSONArray jsonArray = new JSONArray();
        Iterator<ClassToPerson> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.add(iterator.next().getPersonId());
        }

        PersonIdsArray array = new PersonIdsArray();
        int count = personInfoService.findClassToPersonNumByClassId(classId);
        array.setTotalNum(count);
        array.setPersonIdArray(jsonArray);
        return RestfulResult.ok(ResultVOUtil.success(array));
    }

    @GetMapping(value = "/search/{id}/personInfo")
    public ResponseEntity findPersonInfo(@PathVariable String id) {
        PersonInfo personInfo;
        try {
            personInfo = personInfoService.findOne(id);
        } catch (Exception e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(personInfo));
    }

    @PostMapping(value = "edit/{id}/personInfo")
    public ResponseEntity editPersonInfo(@PathVariable("id") String personId, @RequestBody PersonInfo form) {
        PersonInfo personInfo;
        try {
            personInfo = personInfoService.edit(form, personId);

        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(personInfo));
    }

    @GetMapping(value = "search/{id}/medicalHistory")
    public ResponseEntity findMedicalHistory(@PathVariable("id") String personId) {
        MedicalHistory medicalHistory;
        try {
            medicalHistory = medicalHistoryService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(medicalHistory));
    }

    @PostMapping(value = "edit/{id}/medicalHistory")
    public ResponseEntity editMedicalHistory(@PathVariable("id") String personId, @RequestBody MedicalHistory medicalHistory) {
        MedicalHistory result;
        try {
            result = medicalHistoryService.edit(medicalHistory, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    /**
     * 查询体格检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/healthInfo")
    public ResponseEntity findHealthInfo(@PathVariable("id") String personId) {
        HealthInfo healthInfo;
        try {
            healthInfo = healthInfoService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(healthInfo));
    }

    /**
     * 修改体格检查
     *
     * @param personId
     * @param healthInfo
     * @return
     */
    @PostMapping(value = "edit/{id}/healthInfo")
    public ResponseEntity editHealthInfo(@PathVariable("id") String personId, @RequestBody HealthInfo healthInfo) {
        HealthInfo result;
        try {
            result = healthInfoService.edit(healthInfo, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    /**
     * 查询专科检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/specialityCheckup")
    public ResponseEntity findSpecial(@PathVariable("id") String personId) {
        SpecialityCheckup specialityCheckup = null;
        try {
            specialityCheckup = specialityCheckupService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(specialityCheckup));
    }

    /**
     * 修改专科检查
     *
     * @param personId
     * @param specialityCheckup
     * @return
     */
    @PostMapping(value = "edit/{id}/specialityCheckup")
    public ResponseEntity editSpecialityCheckup(@PathVariable("id") String personId, @RequestBody SpecialityCheckup specialityCheckup) {
        SpecialityCheckup result;
        try {
            result = specialityCheckupService.edit(specialityCheckup, personId);
        } catch (Exception e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }

        return RestfulResult.ok(ResultVOUtil.success(result));
    }


    /**
     * 查询实验室器械及检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/laboratoryCheckup")
    public ResponseEntity findLaboraryCheckup(@PathVariable("id") String personId) {
        LaboratoryCheckup laboraryCheckup = null;
        try {
            laboraryCheckup = laboraryCheckupService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(laboraryCheckup));
    }


    /**
     * 修改实验室器械及检查
     *
     * @param personId
     * @param laboraryCheckup
     * @return
     */
    @PostMapping(value = "edit/{id}/laboratoryCheckup")
    public ResponseEntity editLabotoryCheckup(@PathVariable("id") String personId, @RequestBody LaboratoryCheckup laboraryCheckup) {
        LaboratoryCheckup result;
        try {
            result = laboraryCheckupService.edit(laboraryCheckup, personId);
        } catch (Exception e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }


}
