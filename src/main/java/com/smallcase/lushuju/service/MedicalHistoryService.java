package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 19:11
 */
public interface MedicalHistoryService  {

    MedicalHistory findOne(Integer id);

    List<MedicalHistory> findAll();

    MedicalHistory save(MedicalHistory medicalHistory);


}
