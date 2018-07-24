package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/30 11:22
 */
public interface ClinicalExaminationService {
    ClinicalExamination findOne(Integer id);

    List<ClinicalExamination> findAll();

    ClinicalExamination save(ClinicalExamination clinicalExamination);

}
