package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.LaboraryCheckup;
import com.smallcase.lushuju.repository.LaboraryCheckupRepository;
import com.smallcase.lushuju.service.LaboraryCheckupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/28 22:42
 */

@Service
public class LaboraryCheckupServiceImpl implements LaboraryCheckupService {

    @Resource
    private LaboraryCheckupRepository repository;


    @Override
    public LaboraryCheckup findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<LaboraryCheckup> findAll() {
        return repository.findAll();
    }

    @Override
    public LaboraryCheckup save(LaboraryCheckup laboraryCheckup) {
        return repository.save(laboraryCheckup);
    }
}
