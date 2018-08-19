package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import com.smallcase.lushuju.utils.MyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/7 16:22
 */
public interface FaceBedCheckupService {
    FaceBedCheckup findOne(Integer id);

    List<FaceBedCheckup> findAll();

    ResponseEntity save(FaceBedCheckup faceBedCheckup);

    FaceBedCheckup findByPersonId(String personId);

    void edit(FaceBedCheckup faceBedCheckup, String personId) throws MyException;

}
