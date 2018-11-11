package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.FaceCheck;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service
 * date: 2018/11/1 0:16
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface FaceCheckService {
    FaceCheck findOne(Integer id);

    List<FaceCheck> findAll();

    FaceCheck save(FaceCheck faceCheck) throws MyException;

    FaceCheck findByPersonId(String personId) throws MyException, NoDataException;

    FaceCheck edit(FaceCheck faceCheck, String personId) throws MyException;
}
