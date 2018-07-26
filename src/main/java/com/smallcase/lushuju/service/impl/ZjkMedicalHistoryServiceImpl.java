package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import com.smallcase.lushuju.repository.ZjkMedicalHistoryRepository;
import com.smallcase.lushuju.service.ZjkMedicalHistoryService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ZjkMedicalHistory save(ZjkMedicalHistory zjkMedicalHistory) {
        return repository.save(zjkMedicalHistory);
    }


    @Override
    public ZjkMedicalHistory findByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }

    @Override
    @Transactional
    public void edit(ZjkMedicalHistory form, String personId) throws MyException{
        ZjkMedicalHistory zjkMedicalHistory = repository.findByPersonId(personId);
        BeanUtil.copyPropertiesIgnoreNull(form, zjkMedicalHistory);
        ZjkMedicalHistory result = repository.save(zjkMedicalHistory);
        if (result == null) {
            log.error("【修改数据】:MedicalHistory，出错");
            throw new MyException("修改数据出错");
        }
    }

}
