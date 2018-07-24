package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.Detail;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/1 18:46
 */
public interface DetailService {

    Detail findOne(Integer id);

    List<Detail> findAll();

    Detail save(Detail detail);

}
