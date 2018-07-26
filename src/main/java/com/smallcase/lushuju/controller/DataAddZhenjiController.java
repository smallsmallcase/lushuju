package com.smallcase.lushuju.controller;

import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.view.ResultVO;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
        zjkMedicalHistoryService.save(zjkMedicalHistory);
        return RestfulResult.ok();
    }

    @PostMapping(value = "/{id}/faceBedCheck")
    public ResponseEntity addfaceBedCheck(@PathVariable("id") String personId,@RequestBody FaceBedCheckup faceBedCheckup) {
        faceBedCheckup.setPersonId(personId);
//        if (result.hasErrors()) {
//            List<ObjectError> errorList = result.getAllErrors();
//            List<String> errBody = errorList.stream().map(err -> err.getObjectName()).collect(Collectors.toList());
//            errBody.forEach(string -> System.out.println(string));
//            return null;
//        }
        faceBedCheckupService.save(faceBedCheckup);
        return RestfulResult.ok();
    }

    @PostMapping(value = "/{id}/clinicalExamination")
    public ResponseEntity addClinicalExamination(@PathVariable("id") String personId,@RequestBody ClinicalExamination clinicalExamination) {
        clinicalExamination.setPersonId(personId);
        clinicalExaminationService.save(clinicalExamination);
        return RestfulResult.ok();
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
