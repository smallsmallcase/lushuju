package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.repository.ClinicalExaminationRepository;
import com.smallcase.lushuju.service.ClinicalExaminationService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/30 11:23
 */

@Service
@Slf4j
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
    public ResponseEntity save(ClinicalExamination clinicalExamination) {
        try {
            ClinicalExamination result = repository.save(clinicalExamination);
            return RestfulResult.ok(result.getPersonId());


        } catch (DataIntegrityViolationException e) {
            return RestfulResult.serviceErr(0);
        }
    }

    @Override
    public ClinicalExamination findByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }

    @Override
    @Transactional
    public void edit(ClinicalExamination form, String personId) throws MyException {
        ClinicalExamination clinicalExamination = repository.findByPersonId(personId);
        if (clinicalExamination == null) {
            log.error("【修改面部检查和关节检查】：数据找不到");
            throw new MyException("数据找不到");
        }
        BeanUtil.copyPropertiesIgnoreNull(form, clinicalExamination);
        ClinicalExamination result = repository.save(clinicalExamination);
        if (result == null) {
            log.error("【修改数据】:ClinicalExamination，出错");
            throw new MyException("修改数据出错");
        }
    }
}
