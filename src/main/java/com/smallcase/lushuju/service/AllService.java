package com.smallcase.lushuju.service;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.utils.Exception.MyException;

/**
 * package: com.smallcase.lushuju.service
 * date: 2018/10/23 15:36
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface AllService {

    /**
     * 通过病人的personId查询所有的病例信息
     * @param personId
     * @return
     */
    JSONArray findAllInfoByPersonId(String personId) throws Exception;
}
