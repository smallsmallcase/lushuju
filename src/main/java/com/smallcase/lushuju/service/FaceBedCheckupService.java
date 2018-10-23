package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/7 16:22
 */
public interface FaceBedCheckupService {
    FaceBedCheckup findOne(Integer id);

    List<FaceBedCheckup> findAll();

    FaceBedCheckup save(FaceBedCheckup faceBedCheckup) throws MyException;

    FaceBedCheckup findByPersonId(String personId) throws MyException, NoDataException;

    FaceBedCheckup edit(FaceBedCheckup faceBedCheckup, String personId) throws MyException;

}
