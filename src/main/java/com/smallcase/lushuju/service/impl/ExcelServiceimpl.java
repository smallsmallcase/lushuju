package com.smallcase.lushuju.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.pojo.enums.excelEnum.*;
import com.smallcase.lushuju.repository.PersonInfoRepository;
import com.smallcase.lushuju.service.AllService;
import com.smallcase.lushuju.service.ExcelService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @Author: smallcase
 * @Date: 2018/12/22 22:06
 * @Package com.smallcase.lushuju.service.impl
 */

@Service
public class ExcelServiceimpl implements ExcelService {


    @Autowired
    private AllService allService;

    @Autowired
    private PersonInfoRepository personInfoRepository;

    @Override
    public HSSFWorkbook findDataByPersonIds(List<String> personIds) {


        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);//创建居中格式


        HSSFSheet sheet = hssfWorkbook.createSheet();//创建sheet

        HSSFRow row = sheet.createRow(0);//从标题行开始

        parseTitie(row, cellStyle);//处理标题行


        JSONArray oneArrayData;
        Iterator<String> iterator = personIds.iterator();

        int rowIndex = 1;//数据行从1开始

        // oneArrayData corresponds to one row

        while (iterator.hasNext()) {
            oneArrayData = findOneByPersonId(iterator.next());
            row = sheet.createRow(rowIndex++);
            parseArray(oneArrayData, row);
        }

        return hssfWorkbook;

    }

    @Override
    public List<String> findPersonIdsByPatientId(String patientId) {
        return personInfoRepository.findPersonIdsByPatientId(patientId);
    }

    @Override
    public List<String> findPersonIdsByAge1(Integer minAge) {
        return personInfoRepository.findPersonIdsByAge1(minAge);
    }

    @Override
    public List<String> findPersonIdsByAge2(Integer maxAge) {
        return personInfoRepository.findPersonIdsByAge2(maxAge);
    }

    @Override
    public List<String> findPersonIdsByAge3(Integer minAge, Integer maxAge) {
        return personInfoRepository.findPersonIdsByAge3(minAge, maxAge);
    }

    @Override
    public List<String> findPersonIdsBySex(String sex) {
        return personInfoRepository.findPersonIdsBySex(sex);
    }
    @Override
    public List<String> findPersonIds() {
        return personInfoRepository.findPersonIds();
    }


    private void parseTitie(HSSFRow row,HSSFCellStyle cellStyle) {
        int titleColIndex = 0;
        HSSFCell cell;
        PersonInfoEnum[] personInfoEnums = PersonInfoEnum.values();
        for (PersonInfoEnum anEnum : personInfoEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        MedicalHistoryEnum[] medicalHistoryEnums = MedicalHistoryEnum.values();
        for (MedicalHistoryEnum anEnum : medicalHistoryEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        HealthInfoEnum[] healthInfoEnums = HealthInfoEnum.values();
        for (HealthInfoEnum anEnum : healthInfoEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);

        }

        SpecialCheckupEnum[] specialCheckupEnums = SpecialCheckupEnum.values();
        for (SpecialCheckupEnum anEnum : specialCheckupEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        LaboratoryCheckUpEnum[] laboratoryCheckUpEnums = LaboratoryCheckUpEnum.values();
        for (LaboratoryCheckUpEnum anEnum : laboratoryCheckUpEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        ZjkMedicalHistoryEnum[] zjkMedicalHistoryEnums = ZjkMedicalHistoryEnum.values();
        for (ZjkMedicalHistoryEnum anEnum : zjkMedicalHistoryEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        FaceBedCheckupEnum[] faceBedCheckupEnums = FaceBedCheckupEnum.values();
        for (FaceBedCheckupEnum anEnum : faceBedCheckupEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        FaceCheckEnum[] faceCheckEnums = FaceCheckEnum.values();
        for (FaceCheckEnum anEnum : faceCheckEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

        JointCheckEnum[] jointCheckEnums = JointCheckEnum.values();
        for (JointCheckEnum anEnum : jointCheckEnums) {
            cell = row.createCell(titleColIndex++);
            cell.setCellValue(anEnum.getChName());
            cell.setCellStyle(cellStyle);
        }

    }

    private void parseArray(JSONArray array, HSSFRow row) {
        int ObjIndex = 0;//如，personInfo对应的ObjIndex=0,medicalHistory=1



        BeanMap beanMap;
        //处理personInfo表格信息
        Integer columnIndex = 0;
        PersonInfo personInfo = array.getObject(ObjIndex++, PersonInfo.class);
        if (personInfo != null) {
            beanMap = BeanMap.create(personInfo);
            parsePersonInfo(row,columnIndex,beanMap);
        }


        //处理MedicalHistory
        columnIndex = columnIndex + PersonInfoEnum.values().length;
        MedicalHistory medicalHistory = array.getObject(ObjIndex++, MedicalHistory.class);
        if (medicalHistory != null) {
            beanMap = BeanMap.create(medicalHistory);
            parseMedicalHistory(row, columnIndex, beanMap);
        }

        //处理healthInfo
        columnIndex = columnIndex + MedicalHistoryEnum.values().length;
        HealthInfo healthInfo = array.getObject(ObjIndex++, HealthInfo.class);
        if (healthInfo != null) {
            beanMap = BeanMap.create(healthInfo);
            parseHealInfo(row, columnIndex, beanMap);
        }

        //处理specialCheckUp
        columnIndex = columnIndex + HealthInfoEnum.values().length;
        SpecialityCheckup specialityCheckup = array.getObject(ObjIndex++, SpecialityCheckup.class);
        if (specialityCheckup != null) {
            beanMap = BeanMap.create(specialityCheckup);
            parseSpecialCheckUp(row,columnIndex,beanMap);
        }

        //处理laboratoryChekUp
        columnIndex = columnIndex + SpecialCheckupEnum.values().length;
        LaboratoryCheckup laboratoryCheckup = array.getObject(ObjIndex++, LaboratoryCheckup.class);
        if (laboratoryCheckup != null) {
            beanMap = BeanMap.create(laboratoryCheckup);
            parseLaboratoryCheckUp(row,columnIndex,beanMap);
        }

        //处理正畸科病史
        columnIndex = columnIndex + LaboratoryCheckUpEnum.values().length;
        ZjkMedicalHistory zjkMedicalHistory = array.getObject(ObjIndex++, ZjkMedicalHistory.class);
        if (zjkMedicalHistory != null) {
            beanMap = BeanMap.create(zjkMedicalHistory);
            parseZjkMedicalHistory(row, columnIndex, beanMap);
        }

        //处理faceBedCheckUp
        columnIndex = columnIndex + ZjkMedicalHistoryEnum.values().length;
        FaceBedCheckup faceBedCheckup = array.getObject(ObjIndex++, FaceBedCheckup.class);
        if (faceBedCheckup != null) {
            beanMap = BeanMap.create(faceBedCheckup);
            parseFaceBedCheckUp(row, columnIndex, beanMap);
        }

        //处理FaceCheck
        columnIndex = columnIndex + FaceBedCheckupEnum.values().length;
        FaceCheck faceCheck = array.getObject(ObjIndex++, FaceCheck.class);
        if (faceCheck != null) {
            beanMap = BeanMap.create(faceCheck);
            parseFaceCheck(row, columnIndex, beanMap);
        }
        //处理关节检查
        columnIndex = columnIndex + FaceCheckEnum.values().length;
        JointCheck jointCheck = array.getObject(ObjIndex, JointCheck.class);
        if (jointCheck != null) {
            beanMap = BeanMap.create(jointCheck);
            parseJointCheck(row, columnIndex, beanMap);
        }




    }




    private JSONArray findOneByPersonId(String personId) {
        JSONArray array = null;

        try {
            array = allService.findAllInfoByPersonId(personId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }


    /**
     *
     * @param startColumnIndex，cell开始的列数
     * @param personInfo
     */
    private void parsePersonInfo(HSSFRow row,Integer startColumnIndex,BeanMap personInfo){

        PersonInfoEnum[] personInfoEnums = PersonInfoEnum.values();
        HSSFCell cell;
        for (int i = 0; i < personInfoEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            try {
                cell.setCellValue((String) personInfo.get(personInfoEnums[i].getEngName()));
            } catch (ClassCastException e) {
                cell.setCellValue(Integer.toString((int) personInfo.get(personInfoEnums[i].getEngName())));

            }

            //TODO setCellStyle
        }
    }


    private void parseMedicalHistory(HSSFRow row, Integer startColumnIndex, BeanMap medicalHistory) {
        MedicalHistoryEnum[] medicalHistoryEnums = MedicalHistoryEnum.values();
        HSSFCell cell;
        for (int i = 0; i < medicalHistoryEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) medicalHistory.get(medicalHistoryEnums[i].getEngName()));
            //TODO setCellStyle
        }
    }

    private void parseHealInfo(HSSFRow row, Integer startColumnIndex, BeanMap healInfo) {
        HealthInfoEnum[] healthInfoEnums = HealthInfoEnum.values();
        HSSFCell cell;
        for (int i = 0; i < healthInfoEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) healInfo.get(healthInfoEnums[i].getEngName()));
            //TODO style
        }
    }

    private void parseSpecialCheckUp(HSSFRow row, Integer startColumnIndex, BeanMap specialCheckUp) {
        SpecialCheckupEnum[] specialCheckupEnums = SpecialCheckupEnum.values();
        HSSFCell cell;
        for (int i = 0; i < specialCheckupEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) specialCheckUp.get(specialCheckupEnums[i].getEngName()));
        }
    }

    private void parseLaboratoryCheckUp(HSSFRow row, Integer startColumnIndex, BeanMap laboratoryChckUp) {
        LaboratoryCheckUpEnum[] laboratoryCheckUpEnums = LaboratoryCheckUpEnum.values();
        HSSFCell cell;
        for (int i = 0; i < laboratoryCheckUpEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) laboratoryChckUp.get(laboratoryCheckUpEnums[i].getEnName()));
        }
    }

    private void parseZjkMedicalHistory(HSSFRow row, Integer startColumnIndex, BeanMap zjkMedicalHistory) {
        ZjkMedicalHistoryEnum[] zjkMedicalHistoryEnums = ZjkMedicalHistoryEnum.values();
        HSSFCell cell;
        for (int i = 0; i < zjkMedicalHistoryEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) zjkMedicalHistory.get(zjkMedicalHistoryEnums[i].getEnName()));

        }
    }

    private void parseFaceBedCheckUp(HSSFRow row, Integer startColumnIndex, BeanMap faceBedCheckUp){
        FaceBedCheckupEnum[] faceBedCheckupEnums = FaceBedCheckupEnum.values();
        HSSFCell cell;
        for (int i = 0; i < faceBedCheckupEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) faceBedCheckUp.get(faceBedCheckupEnums[i].getEnName()));
        }
    }

    private void parseFaceCheck(HSSFRow row, Integer startColumnIndex, BeanMap faceCheck) {
        FaceCheckEnum[] faceCheckEnums = FaceCheckEnum.values();
        HSSFCell cell;
        for (int i = 0; i < faceCheckEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) faceCheck.get(faceCheckEnums[i].getEnName()));
        }
    }

    private void parseJointCheck(HSSFRow row, Integer startColumnIndex, BeanMap jointCheck) {
        JointCheckEnum[] jointCheckEnums = JointCheckEnum.values();
        HSSFCell cell;
        for (int i = 0; i < jointCheckEnums.length; i++) {
            cell = row.createCell(startColumnIndex + i);
            cell.setCellValue((String) jointCheck.get(jointCheckEnums[i].getEnName()));
        }
    }

}
