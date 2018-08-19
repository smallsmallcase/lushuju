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
    public MedicalHistory findByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }

    @Override
    public List<MedicalHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity save(MedicalHistory medicalHistory){
        try {
            MedicalHistory result = repository.save(medicalHistory);
            return RestfulResult.ok(result.getPersonId());


        } catch (DataIntegrityViolationException e) {
            return RestfulResult.serviceErr(0);
        }
    }

    /**修改数据*/
    @Override
    @Transactional
    public void edit(MedicalHistory form, String personId) throws MyException {
        MedicalHistory medicalHistory = repository.findByPersonId(personId);
        BeanUtil.copyPropertiesIgnoreNull(form, medicalHistory);
        MedicalHistory result = repository.save(medicalHistory);
        if (result == null) {
            log.error("【修改数据】:MedicalHistory，出错");
            throw new MyException("修改数据出错");
        }
    }
}
