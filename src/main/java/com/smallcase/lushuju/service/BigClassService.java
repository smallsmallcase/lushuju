package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.BigClass;
import com.smallcase.lushuju.utils.Exception.MyException;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service
 * date: 2018/11/27 19:18
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface BigClassService {
    BigClass insertBigClass(BigClass bigClass);

    BigClass findBigClassById(Integer id);

    BigClass findBigClassByName(String className) throws MyException;

    boolean checkExist(String className);


    BigClass edit(BigClass bigClass) throws MyException;


    List<BigClass> findAll();

    void deleteOne(Integer id);
}
