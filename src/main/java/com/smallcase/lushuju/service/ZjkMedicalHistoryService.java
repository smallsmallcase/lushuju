package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/30 10:44
 */
public interface ZjkMedicalHistoryService {
    ZjkMedicalHistory findOne(Integer id);

    List<ZjkMedicalHistory> findAll();

    ZjkMedicalHistory save(ZjkMedicalHistory zjkMedicalHistory) throws MyException;

    ZjkMedicalHistory findByPersonId(String personId) throws MyException, NoDataException;

    ZjkMedicalHistory edit(ZjkMedicalHistory zjkMedicalHistory, String personId) throws MyException;


}
