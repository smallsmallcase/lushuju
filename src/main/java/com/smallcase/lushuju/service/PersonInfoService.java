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

    PersonInfo findOne(String id) throws Exception;

    List<PersonInfo> findAll();

    PersonInfo save(PersonInfo personInfo) throws MyException;

    PersonInfo edit(PersonInfoForm form, String personId) throws MyException;

    /*
    插入图片地址
     */
    void insertImg(String imgPath, String fileName,String personId) throws RuntimeException;

    /*
    根据personId获取对应的图片子地址
     */
    String getImgPath(String personId) throws RuntimeException;

    String getFileName(String personId) throws RuntimeException;


}
