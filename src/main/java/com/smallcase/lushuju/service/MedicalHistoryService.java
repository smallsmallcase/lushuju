package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.utils.MyException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 19:11
 */
public interface MedicalHistoryService  {

    MedicalHistory findOne(Integer id);

    MedicalHistory findByPersonId(String personId);

    List<MedicalHistory> findAll();

    ResponseEntity save(MedicalHistory medicalHistory);

    void edit(MedicalHistory medicalHistory, String personId) throws MyException;

}
