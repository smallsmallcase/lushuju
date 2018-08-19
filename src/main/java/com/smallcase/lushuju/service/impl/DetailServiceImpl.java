package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.Detail;
import com.smallcase.lushuju.repository.DetailRepository;
import com.smallcase.lushuju.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/1 18:47
 */


@Deprecated
@Service
public class DetailServiceImpl implements DetailService{

    @Autowired
    private DetailRepository repository;

    @Override
    public Detail findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<Detail> findAll() {
        return repository.findAll();
    }

    @Override
    public Detail save(Detail detail) {
        return repository.save(detail);
    }
}
