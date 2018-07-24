package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.repository.MedicalHistoryRepository;
import com.smallcase.lushuju.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 19:13
 */

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository repository;

    @Override
    public MedicalHistory findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<MedicalHistory> findAll() {
        return repository.findAll();
    }

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) {
        return repository.save(medicalHistory);
    }
}
