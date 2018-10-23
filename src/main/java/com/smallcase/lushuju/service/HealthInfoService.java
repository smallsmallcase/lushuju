package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.HealthInfo;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 19:51
 */
public interface HealthInfoService {

    HealthInfo findOne(Integer id);

    List<HealthInfo> findAll();

    HealthInfo save(HealthInfo healthInfo) throws MyException;

    HealthInfo findByPersonId(String personId) throws MyException, NoDataException;

    HealthInfo edit(HealthInfo healthInfo, String personId) throws MyException;


}
