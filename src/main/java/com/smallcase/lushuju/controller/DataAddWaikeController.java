package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

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
    private LaboraryCheckupService laboraryCheckupService;


    @PostMapping(value = "/personInfo")
    public ResponseEntity addPersonInfo(@RequestBody PersonInfo personInfo){
//        PersonInfo result = null;
//        try {
//            result = personInfoService.save(personInfo);
//        } catch (MyException e) {
//            return RestfulResult.serviceErr(e.getCode());
//        }
//        return RestfulResult.ok(result.getId());

        return personInfoService.save(personInfo);
    }

    @PostMapping(value = "/{id}/medicalHistory")
    public ResponseEntity addMedicalHistory(@PathVariable("id") String personId,@RequestBody MedicalHistory medicalHistory) {
        medicalHistory.setPersonId(personId);
//        MedicalHistory result;
//        try {
//            result = medicalHistoryService.save(medicalHistory);
//        } catch (MyException e) {
//            return RestfulResult.serviceErr(e.getCode());
//        }
//        return RestfulResult.ok(result);
        return medicalHistoryService.save(medicalHistory);
    }

    @PostMapping(value = "/{id}/healthInfo")
    public ResponseEntity addHealthInfo(@PathVariable("id") String personId, @RequestBody HealthInfo healthInfo) {
        healthInfo.setPersonId(personId);
//        HealthInfo result = null;
//
//        try {
//            result = healthInfoService.save(healthInfo);
//        } catch (MyException e) {
//            return RestfulResult.serviceErr(e.getMessage());
//        }
//        return RestfulResult.ok(result);

        return healthInfoService.save(healthInfo);
    }

    @PostMapping(value = "/{id}/specialityCheckup")
    public ResponseEntity addSpecialityCheckup(@PathVariable("id") String personId, @RequestBody SpecialityCheckup specialityCheckup) {
        specialityCheckup.setPersonId(personId);
//
//        SpecialityCheckup result;
//        try {
//            result = specialityCheckupService.save(specialityCheckup);
//        } catch (MyException e) {
//            return RestfulResult.serviceErr(e.getMessage());
//        }
//        return RestfulResult.ok(result);

        return specialityCheckupService.save(specialityCheckup);
    }

    @PostMapping(value = "/{id}/laboraryCheckup")
    public ResponseEntity addLaboraryCheckup(@PathVariable("id") String personId,@RequestBody LaboraryCheckup laboraryCheckup, HttpSession session) {
        laboraryCheckup.setPersonId(personId);
//        laboraryCheckupService.save(laboraryCheckup);
//        return RestfulResult.ok();

        return laboraryCheckupService.save(laboraryCheckup);
    }
}
