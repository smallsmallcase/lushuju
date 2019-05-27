package com.smallcase.lushuju.service;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.pojo.entity.ClassToPerson;
import com.smallcase.lushuju.pojo.entity.PersonInfo;
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

    List<PersonInfo> findListByids(JSONArray array) throws MyException;

    List<PersonInfo> findAll();

    boolean checkExisted(String patientId);

    boolean checkBigClassExisted(String personId);

    String findPersonIdByPatientId(String patientId);

    void deleteOnePersonByPersonId(String personId);

    List<ClassToPerson> findClassToPersonByPersonId(String personId);

    List<ClassToPerson> findClassToPersonByClassId(Integer classId, int pageSize, int pageNum);

    int findClassToPersonNumByClassId(Integer classId);

    void deleteAllBigClassByPersonId(String personId) throws MyException;

    PersonInfo save(PersonInfo personInfo) throws MyException;

    void insertBigClass(List<ClassToPerson> classToPersonList) throws MyException;

    PersonInfo edit(PersonInfo form, String personId) throws MyException;

    /*
    插入图片地址
     */
    void insertImg(String imgPath,String fileName,String personId) throws RuntimeException;

    /*
    根据personId获取对应的图片子地址
     */
    String getImgPath(String personId) throws RuntimeException;

    String getFileName(String personId) throws RuntimeException;


}
