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
    ClinicalExamination findOne(Integer id) throws Exception;

    List<ClinicalExamination> findAll() throws Exception;

    ResponseEntity save(ClinicalExamination clinicalExamination) throws MyException;

    ClinicalExamination findByPersonId(String personId) throws MyException;

    void edit(ClinicalExamination clinicalExamination, String personId) throws MyException;



}
