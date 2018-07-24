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
    public ResponseEntity addPersonInfo(@RequestBody PersonInfoForm form) throws MyException {
        PersonInfo personInfo = new PersonInfo();
        BeanUtils.copyProperties(form, personInfo);
        personInfoService.save(personInfo);
        return RestfulResult.createSuccess();
    }


    @PostMapping(value = "/{id}/medicalHistory")
    public ResponseEntity addMedicalHistory(@PathVariable("id") String personId,@RequestBody MedicalHistory medicalHistory) {
        medicalHistory.setPersonId(personId);
        medicalHistoryService.save(medicalHistory);
        return RestfulResult.createSuccess();
    }

    @PostMapping(value = "/{id}/healthInfo")
    public ResultVO<HealthInfo> addHealthInfo(@RequestBody HealthInfo healthInfo, HttpSession session) {
        String personId = (String)session.getAttribute("personId");
        healthInfo.setPersonId(personId);
        HealthInfo content = healthInfoService.save(healthInfo);
        return ResultVOUtil.success(content);
    }

    @PostMapping(value = "/specialityCheckup")
    public ResultVO<SpecialityCheckup> addSpecialityCheckup(@RequestBody SpecialityCheckup specialityCheckup, HttpSession session) {
        String personId = (String)session.getAttribute("personId");
        specialityCheckup.setPersonId(personId);
        SpecialityCheckup content = specialityCheckupService.save(specialityCheckup);
        return ResultVOUtil.success(content);
    }

    @PostMapping(value = "/laboraryCheckup")
    public ResultVO<LaboraryCheckup> addLaboraryCheckup(@RequestBody LaboraryCheckup laboraryCheckup, HttpSession session) {
        String personId = (String)session.getAttribute("personId");
        laboraryCheckup.setPersonId(personId);
        LaboraryCheckup content = laboraryCheckupService.save(laboraryCheckup);
        return ResultVOUtil.success(content);
    }
}
