package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.SpecialityCheckup;
import com.smallcase.lushuju.repository.SpecialityCheckupRepository;
import com.smallcase.lushuju.service.SpecialityCheckupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 21:54
 */

@Service
public class SpecialityServiceImpl implements SpecialityCheckupService {

    @Autowired
    private SpecialityCheckupRepository repository;
    @Override
    public SpecialityCheckup findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<SpecialityCheckup> findAll() {
        return repository.findAll();
    }

    @Override
    public SpecialityCheckup save(SpecialityCheckup specialityCheckup) {
        return repository.save(specialityCheckup);
    }
}
