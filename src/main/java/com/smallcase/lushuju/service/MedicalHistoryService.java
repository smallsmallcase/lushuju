package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 19:11
 */
public interface MedicalHistoryService  {

    MedicalHistory findOne(Integer id);

    MedicalHistory findByPersonId(String personId) throws MyException, NoDataException;

    List<MedicalHistory> findAll();

    MedicalHistory save(MedicalHistory medicalHistory) throws MyException;

    MedicalHistory edit(MedicalHistory medicalHistory, String personId) throws MyException;

}
