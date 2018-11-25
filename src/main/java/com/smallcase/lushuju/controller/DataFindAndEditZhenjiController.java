package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
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
    private FaceCheckService faceCheckService;
    @Autowired
    private JointCheckService jointCheckService;

    /**
     * 查询正畸科病史
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/zjkMedicalHistory")
    public ResponseEntity findZjkMedicalHistory(@PathVariable("id") String personId) {
        ZjkMedicalHistory zjkMedicalHistory = null;
        try {
            zjkMedicalHistory = zjkMedicalHistoryService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(zjkMedicalHistory));
    }

    /**
     * 修改正畸科病史
     *
     * @param personId
     * @param zjkMedicalHistory
     * @return
     */
    @PostMapping(value = "edit/{id}/zjkMedicalHistory")
    public ResponseEntity editZjkMedicalHistory(@PathVariable("id") String personId, @RequestBody ZjkMedicalHistory zjkMedicalHistory) {
        ZjkMedicalHistory result;
        try {
            result = zjkMedicalHistoryService.edit(zjkMedicalHistory, personId);
        } catch (Exception e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    /**
     * 查询临床检查
     *
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/faceBedCheck")
    public ResponseEntity findFaceBedCheck(@PathVariable("id") String personId) {
        FaceBedCheckup faceBedCheckup;
        try {
            faceBedCheckup = faceBedCheckupService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(faceBedCheckup));
    }

    /**
     * 修改临床检查
     *
     * @param personId
     * @param form
     * @return
     */
    @PostMapping(value = "edit/{id}/faceBedCheck")
    public ResponseEntity editfaceBedCheck(@PathVariable("id") String personId, @RequestBody FaceBedCheckup form) {
        FaceBedCheckup faceBedCheckup;
        try {
            faceBedCheckup = faceBedCheckupService.edit(form, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(faceBedCheckup));
    }


    /**
     * 查询面部检查
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/faceCheck")
    public ResponseEntity findClinicalExamination(@PathVariable("id") String personId) {
        FaceCheck faceCheck;
        try {
            faceCheck = faceCheckService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(faceCheck));
    }

    /**
     * 修改面部检查
     * @param personId
     * @param faceCheck
     * @return
     */
    @PostMapping(value = "edit/{id}/faceCheck")
    public ResponseEntity editClinicalExamination(@PathVariable("id") String personId, @RequestBody FaceCheck faceCheck) {
        FaceCheck result;
        try {
            result = faceCheckService.edit(faceCheck, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    /**
     * 查询关节检查
     * @param personId
     * @return
     */
    @GetMapping(value = "search/{id}/jointCheck")
    public ResponseEntity findJointCheck(@PathVariable("id") String personId) {
        JointCheck jointCheck;
        try {
            jointCheck = jointCheckService.findByPersonId(personId);
        } catch (Exception e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(jointCheck));
    }

    @PostMapping(value = "edit/{id}/jointCheck")
    public ResponseEntity editJointCheck(@PathVariable("id") String personId, @RequestBody JointCheck jointCheck) {
        JointCheck result;
        try {
            result = jointCheckService.edit(jointCheck, personId);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

}
