package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.FigCheck;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/1 18:28
 */
public interface FigCheckService {

    FigCheck findOne(Integer id);

    List<FigCheck> findAll();

    FigCheck save(FigCheck figCheck);

}
