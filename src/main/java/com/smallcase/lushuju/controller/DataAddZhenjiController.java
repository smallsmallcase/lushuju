package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Package: com.smallcase.lushuju.controller
 * Author: smallcase
 * Date: Created in 2018/7/6 15:38
 */


@RestController
@RequestMapping(path = "/zhenji/add")
@Slf4j
public class DataAddZhenjiController {

    @Autowired
    private ZjkMedicalHistoryService zjkMedicalHistoryService;

    @Autowired
    private FaceBedCheckupService faceBedCheckupService;

    @Autowired
    private ClinicalExaminationService clinicalExaminationService;

    @Autowired
    private FigCheckService figCheckService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private AnalysisService analysisService;

    @PostMapping(value = "/{id}/zjkMedicalHistory")
    public ResponseEntity addZjkMedicalHistory(@PathVariable("id") String personId,@RequestBody ZjkMedicalHistory zjkMedicalHistory) {
        zjkMedicalHistory.setPersonId(personId);

        ZjkMedicalHistory result;
        try {
            result = zjkMedicalHistoryService.save(zjkMedicalHistory);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/faceBedCheck")
    public ResponseEntity addfaceBedCheck(@PathVariable("id") String personId,@RequestBody FaceBedCheckup faceBedCheckup) {
        faceBedCheckup.setPersonId(personId);
        FaceBedCheckup result = null;
        try {
             result = faceBedCheckupService.save(faceBedCheckup);
        } catch (MyException e) {
            e.printStackTrace();
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));
    }

    @PostMapping(value = "/{id}/clinicalExamination")
    public ResponseEntity addClinicalExamination(@PathVariable("id") String personId,@RequestBody ClinicalExamination clinicalExamination) {
        clinicalExamination.setPersonId(personId);
        ClinicalExamination result;
        try {
             result = clinicalExaminationService.save(clinicalExamination);

        } catch (MyException e) {
            return RestfulResult.serviceErr(ResultVOUtil.error(e.getMessage()));
        }
        return RestfulResult.ok(ResultVOUtil.success(result));

    }

    @Deprecated
    @PostMapping(value = "/figCheck")
    public ResultVO<FigCheck> addFigCheck(@RequestBody FigCheck figCheck, HttpSession session) {
        String personId = (String)session.getAttribute("personId");
        figCheck.setPersonId(personId);
        FigCheck content = figCheckService.save(figCheck);
        return ResultVOUtil.success(content);
    }


    @Deprecated
    @PostMapping(value = "/detail")
    public ResultVO<Detail> addDetail(@RequestBody Detail detail, HttpSession session) {
        String personId = (String)session.getAttribute("personId");
        detail.setPersonId(personId);
        Detail content = detailService.save(detail);
        return ResultVOUtil.success(content);
    }


    @Deprecated
    @PostMapping(value = "/analysis")
    public ResultVO<Analysis> addAnalysis(@RequestBody Analysis analysis, HttpSession session) {
        String personId = (String)session.getAttribute("personId");
        analysis.setPersonId(personId);
        Analysis content = analysisService.save(analysis);
        return ResultVOUtil.success(content);
    }

}
