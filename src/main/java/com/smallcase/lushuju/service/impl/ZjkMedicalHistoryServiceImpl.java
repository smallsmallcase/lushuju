package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import com.smallcase.lushuju.repository.ZjkMedicalHistoryRepository;
import com.smallcase.lushuju.service.ZjkMedicalHistoryService;
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
 * Date: Created in 2018/6/30 10:45
 */

@Service
@Slf4j
public class ZjkMedicalHistoryServiceImpl implements ZjkMedicalHistoryService {

    @Autowired
    private ZjkMedicalHistoryRepository repository;


    @Override
    public ZjkMedicalHistory findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<ZjkMedicalHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public ZjkMedicalHistory save(ZjkMedicalHistory zjkMedicalHistory) throws MyException {
        ZjkMedicalHistory result;
        try {
            result = repository.save(zjkMedicalHistory);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }


    @Override
    public ZjkMedicalHistory findByPersonId(String personId) throws MyException, NoDataException {
        ZjkMedicalHistory medicalHistory;
        try {
            medicalHistory = repository.findByPersonId(personId);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (medicalHistory == null) {
            throw new NoDataException("正畸科数据找不到");
        }
        return medicalHistory;
    }

    @Override
    @Transactional
    public ZjkMedicalHistory edit(ZjkMedicalHistory form, String personId) throws MyException{
        ZjkMedicalHistory zjkMedicalHistory;
        try {
            zjkMedicalHistory = repository.findByPersonId(personId);
            BeanUtil.copyPropertiesIgnoreNull(form, zjkMedicalHistory);
            zjkMedicalHistory = repository.save(zjkMedicalHistory);
            if (zjkMedicalHistory == null) {
                log.error("【修改数据】:ZjkMedicalHistory，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        return zjkMedicalHistory;
    }

}
