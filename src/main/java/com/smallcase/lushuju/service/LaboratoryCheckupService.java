package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.LaboratoryCheckup;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/28 22:39
 */
public interface LaboratoryCheckupService {
    LaboratoryCheckup findOne(Integer id);

    List<LaboratoryCheckup> findAll();

    LaboratoryCheckup save(LaboratoryCheckup laboraryCheckup) throws MyException;

    LaboratoryCheckup findByPersonId(String personId) throws MyException, NoDataException;

    LaboratoryCheckup edit(LaboratoryCheckup laboraryCheckup, String personId) throws MyException;


}
