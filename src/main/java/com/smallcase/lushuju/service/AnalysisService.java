package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.Analysis;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/1 19:06
 */
public interface AnalysisService {
    Analysis findOne(Integer id);

    List<Analysis> findAll();

    Analysis save(Analysis analysis);
}
