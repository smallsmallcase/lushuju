package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import com.smallcase.lushuju.repository.ZjkMedicalHistoryRepository;
import com.smallcase.lushuju.service.ZjkMedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/30 10:45
 */

@Service
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

}
