package com.smallcase.lushuju.service;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/6/18 16:58
 */
public interface PersonInfoService {

    PersonInfo findOne(String id) throws MyException, NoDataException;

    List<PersonInfo> findAll();

    PersonInfo save(PersonInfo personInfo) throws MyException;

    PersonInfo edit(PersonInfoForm form, String personId) throws MyException;

    /**
     * 通过病人的personId查询所有的病例信息
     * @param personId
     * @return
     */
    JSONArray findAllInfoByPersonId(String personId);
}
