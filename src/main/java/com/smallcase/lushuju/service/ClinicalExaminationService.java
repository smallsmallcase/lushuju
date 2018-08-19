package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.utils.MyException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/30 11:22
 */
public interface ClinicalExaminationService {
    ClinicalExamination findOne(Integer id);

    List<ClinicalExamination> findAll();

    ResponseEntity save(ClinicalExamination clinicalExamination);

    ClinicalExamination findByPersonId(String personId);

    void edit(ClinicalExamination clinicalExamination, String personId) throws MyException;



}
