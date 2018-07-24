package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.HealthInfo;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 19:51
 */
public interface HealthInfoService {

    HealthInfo findOne(Integer id);

    List<HealthInfo> findAll();

    HealthInfo save(HealthInfo healthInfo);

}
