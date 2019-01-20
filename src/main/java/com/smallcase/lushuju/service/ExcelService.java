package com.smallcase.lushuju.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @Author: smallcase
 * @Date: 2018/12/29 21:41
 * @Package com.smallcase.lushuju.service
 */
public interface ExcelService {

    HSSFWorkbook findDataByPersonIds(List<String> personIds);

    List<String> findPersonIdsByPatientId(String patientId);

    List<String> findPersonIdsByAge1(Integer minAge);
    List<String> findPersonIdsByAge2(Integer maxAge);
    List<String> findPersonIdsByAge3(Integer minAge,Integer maxAge);
    List<String> findPersonIdsBySex(String sex);

    List<String> findPersonIds();

}
