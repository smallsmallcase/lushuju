package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.repository.ClinicalExaminationRepository;
import com.smallcase.lushuju.service.ClinicalExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/30 11:23
 */

@Service
public class ClinicalExaminationServicceImpl implements ClinicalExaminationService {

    @Autowired
    private ClinicalExaminationRepository repository;
    @Override
    public ClinicalExamination findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<ClinicalExamination> findAll() {
        return repository.findAll();
    }

    @Override
    public ClinicalExamination save(ClinicalExamination clinicalExamination) {
        return repository.save(clinicalExamination);
    }
}
