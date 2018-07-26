package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    private LaboraryCheckupService laboraryCheckupService;


    @GetMapping(value = "/search/{id}/personInfo")
    public ResponseEntity findPersonInfo(@PathVariable String id) {
        PersonInfo personInfo = personInfoService.findOne(id);
        return RestfulResult.ok(personInfo);
    }

    @PatchMapping(value = "edit/{id}/personInfo")
    public ResponseEntity editPersonInfo(@PathVariable("id") String personId, @RequestBody PersonInfoForm form) {
        try {
            personInfoService.edit(form, personId);

        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }

        return RestfulResult.ok();
    }

    @GetMapping(value = "search/{id}/medicalHistory")
    public ResponseEntity findMedicalHistory(@PathVariable("id") String personId) {
        MedicalHistory medicalHistory = medicalHistoryService.findByPersonId(personId);
        return RestfulResult.ok(medicalHistory);
    }

    @PatchMapping(value = "edit/{id}/medicalHistory")
    public ResponseEntity editMedicalHistory(@PathVariable("id") String personId, @RequestBody MedicalHistory medicalHistory) {
        try {
            medicalHistoryService.edit(medicalHistory, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }

        return RestfulResult.ok();
    }

    /**
     * 查询体格检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/healthInfo")
    public ResponseEntity findHealthInfo(@PathVariable("id") String personId) {
        HealthInfo healthInfo = healthInfoService.findByPersonId(personId);
        return RestfulResult.ok(healthInfo);
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
        try {
            healthInfoService.edit(healthInfo, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }

        return RestfulResult.ok();
    }

    /**
     * 查询专科检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/specialityCheckup")
    public ResponseEntity findSpecial(@PathVariable("id") String personId) {
        SpecialityCheckup specialityCheckup = specialityCheckupService.findByPersonId(personId);
        return RestfulResult.ok(specialityCheckup);
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
        try {
            specialityCheckupService.edit(specialityCheckup, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }
        return RestfulResult.ok();
    }


    /**
     * 查询实验室器械及检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/laboraryCheckup")
    public ResponseEntity findLaboraryCheckup(@PathVariable("id") String personId) {
        LaboraryCheckup laboraryCheckup = laboraryCheckupService.findByPersonId(personId);
        return RestfulResult.ok(laboraryCheckup);
    }


    /**
     * 修改实验室器械及检查
     *
     * @param personId
     * @param laboraryCheckup
     * @return
     */
    @PatchMapping(value = "edit/{id}/laboraryCheckup")
    public ResponseEntity editLabotoryCheckup(@PathVariable("id") String personId, @RequestBody LaboraryCheckup laboraryCheckup) {
        try {
            laboraryCheckupService.edit(laboraryCheckup, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }
        return RestfulResult.ok();
    }


}
