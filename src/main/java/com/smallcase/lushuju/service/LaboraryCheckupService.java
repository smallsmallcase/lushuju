package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.LaboraryCheckup;
import com.smallcase.lushuju.utils.MyException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/28 22:39
 */
public interface LaboraryCheckupService {
    LaboraryCheckup findOne(Integer id);

    List<LaboraryCheckup> findAll();

    LaboraryCheckup save(LaboraryCheckup laboraryCheckup);

    LaboraryCheckup findByPersonId(String personId);

    void edit(LaboraryCheckup laboraryCheckup, String personId) throws MyException;


}
