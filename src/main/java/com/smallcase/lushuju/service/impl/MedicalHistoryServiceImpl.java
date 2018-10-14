package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.repository.MedicalHistoryRepository;
import com.smallcase.lushuju.service.MedicalHistoryService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import com.smallcase.lushuju.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 19:13
 */

@Service
@Slf4j
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository repository;

    @Override
    public MedicalHistory findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public MedicalHistory findByPersonId(String personId) throws MyException {
        MedicalHistory medicalHistory;
        try {
            medicalHistory = repository.findByPersonId(personId);
            if (medicalHistory == null) {
                throw new MyException("MedicalHistory找不到");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        return medicalHistory;

    }

    @Override
    public List<MedicalHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) throws MyException {
        MedicalHistory result;
        try {
            result = repository.save(medicalHistory);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }

    /**修改数据*/
    @Override
    @Transactional
    public MedicalHistory edit(MedicalHistory form, String personId) throws MyException {
        MedicalHistory medicalHistory;

        try {
            medicalHistory = repository.findByPersonId(personId);
            BeanUtil.copyPropertiesIgnoreNull(form, medicalHistory);
            medicalHistory = repository.save(medicalHistory);
            if (medicalHistory == null) {
                log.error("【修改数据】:MedicalHistory，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        return medicalHistory;
    }
}
