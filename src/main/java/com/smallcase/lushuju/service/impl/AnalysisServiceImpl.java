package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.Analysis;
import com.smallcase.lushuju.repository.AnalysisRepository;
import com.smallcase.lushuju.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/1 19:07
 */


@Deprecated
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisRepository repository;

    @Override
    public Analysis findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<Analysis> findAll() {
        return repository.findAll();
    }

    @Override
    public Analysis save(Analysis analysis) {
        return repository.save(analysis);
    }
}
