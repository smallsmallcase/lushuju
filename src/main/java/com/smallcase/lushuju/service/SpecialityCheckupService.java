package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.SpecialityCheckup;
import com.smallcase.lushuju.utils.MyException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 21:52
 */
public interface SpecialityCheckupService {
    SpecialityCheckup findOne(Integer id);

    List<SpecialityCheckup> findAll();

    SpecialityCheckup save(SpecialityCheckup specialityCheckup) throws MyException;

    SpecialityCheckup findByPersonId(String personId) throws MyException;

    SpecialityCheckup edit(SpecialityCheckup specialityCheckup, String personId) throws MyException;

}
