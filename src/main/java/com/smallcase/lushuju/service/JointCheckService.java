package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.JointCheck;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service
 * date: 2018/11/1 0:28
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface JointCheckService {
    JointCheck findOne(Integer id);

    List<JointCheck> findAll();

    boolean checkExisted(String personId);

    JointCheck save(JointCheck faceCheck) throws MyException;

    JointCheck findByPersonId(String personId) throws MyException, NoDataException;

    JointCheck edit(JointCheck faceCheck, String personId) throws MyException;
}
