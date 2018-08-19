package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.utils.MyException;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 16:58
 */
public interface PersonInfoService {

    PersonInfo findOne(String id);

    List<PersonInfo> findAll();

    ResponseEntity save(PersonInfo personInfo);

    void edit(PersonInfoForm form, String personId) throws MyException;
}
