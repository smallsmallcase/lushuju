package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "edit/{id}/personInfo")
    public ResponseEntity editPersonInfo(@PathVariable("id") String personId, @RequestBody PersonInfoForm form) {
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

    @PatchMapping(value = "edit/{id}/medicalHistory")
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
    @PatchMapping(value = "edit/{id}/healthInfo")
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
    @PatchMapping(value = "edit/{id}/specialityCheckup")
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
    @PatchMapping(value = "edit/{id}/laboratoryCheckup")
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
