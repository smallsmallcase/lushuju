package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import com.smallcase.lushuju.service.ClinicalExaminationService;
import com.smallcase.lushuju.service.FaceBedCheckupService;
import com.smallcase.lushuju.service.ZjkMedicalHistoryService;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/25 15:53
 */
@RestController
@RequestMapping(path = "zhenji")
@Slf4j
public class DataFindAndEditZhenjiController {

    @Autowired
    private ZjkMedicalHistoryService zjkMedicalHistoryService;
    @Autowired
    private FaceBedCheckupService faceBedCheckupService;
    @Autowired
    private ClinicalExaminationService clinicalExaminationService;

    /**
     * 查询正畸科病史
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/zjkMedicalHistory")
    public ResponseEntity findZjkMedicalHistory(@PathVariable("id") String personId) {
        ZjkMedicalHistory zjkMedicalHistory = zjkMedicalHistoryService.findByPersonId(personId);
        return RestfulResult.ok(zjkMedicalHistory);
    }

    /**
     * 修改正畸科病史
     *
     * @param personId
     * @param zjkMedicalHistory
     * @return
     */
    @PatchMapping(value = "edit/{id}/zjkMedicalHistory")
    public ResponseEntity editZjkMedicalHistory(@PathVariable("id") String personId, @RequestBody ZjkMedicalHistory zjkMedicalHistory) {
        try {
            zjkMedicalHistoryService.edit(zjkMedicalHistory, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }
        return RestfulResult.ok();
    }

    /**
     * 查询临床检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/faceBedCheck")
    public ResponseEntity findFaceBedCheck(@PathVariable("id") String personId) {
        FaceBedCheckup faceBedCheckup = faceBedCheckupService.findByPersonId(personId);
        return RestfulResult.ok(faceBedCheckup);
    }

    /**
     * 修改临床检查
     *
     * @param personId
     * @param faceBedCheckup
     * @return
     */
    @PatchMapping(value = "edit/{id}/faceBedCheck")
    public ResponseEntity editfaceBedCheck(@PathVariable("id") String personId, @RequestBody FaceBedCheckup faceBedCheckup) {
        try {
            faceBedCheckupService.edit(faceBedCheckup, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }
        return RestfulResult.ok();
    }


    /**
     * 查询面部检查和关节检查
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/clinicalExamination")
    public ResponseEntity findClinicalExamination(@PathVariable("id") String personId) {
        ClinicalExamination clinicalExamination = clinicalExaminationService.findByPersonId(personId);
        return RestfulResult.ok(clinicalExamination);
    }

    /**
     * 修改面部检查和关节检查
     * @param personId
     * @param clinicalExamination
     * @return
     */
    @PatchMapping(value = "edit/{id}/clinicalExamination")
    public ResponseEntity editClinicalExamination(@PathVariable("id") String personId, @RequestBody ClinicalExamination clinicalExamination) {
        try {
            clinicalExaminationService.edit(clinicalExamination, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr();
        }
        return RestfulResult.ok();
    }
}
