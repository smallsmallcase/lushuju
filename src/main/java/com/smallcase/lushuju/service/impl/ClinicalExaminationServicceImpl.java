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
    public ClinicalExamination findOne(Integer id) throws Exception {
        ClinicalExamination one = repository.findOne(id);
        if (one != null && one.getId() != null) {
            return one;
        } else {
            throw new Exception("ClinicalExamination数据访问不到");
        }
    }

    @Override
    public List<ClinicalExamination> findAll() throws Exception {
        List<ClinicalExamination> all = repository.findAll();
        if (all.size() <= 0) {
            throw new Exception("ClinicalExamination列表访问不到");
        } else {
            return all;
        }
    }

    @Override
    public ClinicalExamination save(ClinicalExamination clinicalExamination) throws MyException {
        try {
            ClinicalExamination result = repository.save(clinicalExamination);
            return result;


        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public ClinicalExamination findByPersonId(String personId) throws MyException {
        ClinicalExamination clinicalExamination;
        try {
            clinicalExamination = repository.findByPersonId(personId);
            if (clinicalExamination == null) {
                throw new MyException("通过personId,ClinicalExamination数据访问不到");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        return clinicalExamination;

    }

    @Override
    @Transactional
    public ClinicalExamination edit(ClinicalExamination form, String personId) throws MyException {
        ClinicalExamination clinicalExamination;
        try {
            clinicalExamination = repository.findByPersonId(personId);
            if (clinicalExamination == null) {
                log.error("【修改面部检查和关节检查】：数据找不到");
                throw new MyException("数据找不到");
            }
            BeanUtil.copyPropertiesIgnoreNull(form, clinicalExamination);
            clinicalExamination = repository.save(clinicalExamination);
            if (clinicalExamination == null) {
                log.error("【修改数据】:ClinicalExamination，保存出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage() + "【修改数据】:ClinicalExamination");
        }

        return clinicalExamination;

    }
}
