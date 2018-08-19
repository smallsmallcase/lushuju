package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.FigCheck;
import com.smallcase.lushuju.repository.FigCheckRepository;
import com.smallcase.lushuju.service.FigCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/1 18:29
 */


@Deprecated
@Service
public class FigCheckServiceImpl implements FigCheckService{

    @Autowired
    private FigCheckRepository repository;

    @Override
    public FigCheck findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<FigCheck> findAll() {
        return repository.findAll();
    }

    @Override
    public FigCheck save(FigCheck figCheck) {
        return repository.save(figCheck);
    }
}
