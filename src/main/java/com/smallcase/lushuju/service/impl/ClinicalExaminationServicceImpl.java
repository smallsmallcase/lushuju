package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.repository.ClinicalExaminationRepository;
import com.smallcase.lushuju.service.ClinicalExaminationService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ClinicalExamination findOne(Integer id) throws NoDataException {
        ClinicalExamination one = repository.findOne(id);
        if (one != null && one.getId() != null) {
            return one;
        } else {
            throw new NoDataException("ClinicalExamination数据访问不到");
        }
    }

    @Override
    public List<ClinicalExamination> findAll() throws NoDataException {
        List<ClinicalExamination> all = repository.findAll();
        if (all.size() <= 0) {
            throw new NoDataException("ClinicalExamination列表访问不到");
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
    public ClinicalExamination findByPersonId(String personId) throws MyException,NoDataException {
        ClinicalExamination clinicalExamination;
        try {
            clinicalExamination = repository.findByPersonId(personId);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        if (clinicalExamination == null) {
            throw new NoDataException("通过personId,ClinicalExamination数据访问不到");
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
