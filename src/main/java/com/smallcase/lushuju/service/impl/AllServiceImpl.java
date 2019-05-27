package com.smallcase.lushuju.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smallcase.lushuju.pojo.entity.*;
import com.smallcase.lushuju.service.*;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service.impl
 * date: 2018/10/23 15:37
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Service
public class AllServiceImpl implements AllService {
    @Autowired
    private PersonInfoService personInfoService;
    @Autowired
    private MedicalHistoryService medicalHistoryService;
    @Autowired
    private HealthInfoService healthInfoService;
    @Autowired
    private SpecialityCheckupService specialityCheckupService;
    @Autowired
    private LaboratoryCheckupService laboratoryCheckupService;
    @Autowired
    private ZjkMedicalHistoryService zjkMedicalHistoryService;
    @Autowired
    private FaceBedCheckupService faceBedCheckupService;
//    @Autowired
//    private ClinicalExaminationService clinicalExaminationService;

    @Autowired
    private FaceCheckService faceCheckService;

    @Autowired
    private JointCheckService jointCheckService;
    @Autowired
    private BigClassService bigClassService;





    @Override
    public JSONArray findAllInfoByPersonId(String personId) throws Exception {
        JSONArray jsonArray = new JSONArray();

        PersonInfo personInfo;

        try {
            List<ClassToPerson> classToPersonList = personInfoService.findClassToPersonByPersonId(personId);
            String name = "";
            StringBuilder sb = new StringBuilder();
            for (ClassToPerson one : classToPersonList) {
                name = bigClassService.findBigClassById(one.getClassId()).getClassName();
                sb.append(name).append('、');

            }
            if (sb.length() - 1 < 1) {
                jsonArray.add(" ");
            } else {
                jsonArray.add(sb.toString().substring(0, sb.length() - 1));
            }
            personInfo = personInfoService.findOne(personId);
        } catch (NoDataException e) {
            throw e;
        }
        jsonArray.add(personInfo);



        MedicalHistory medicalHistory;
        try {
            medicalHistory = medicalHistoryService.findByPersonId(personId);
        } catch (NoDataException e) {
            medicalHistory = null;
        } catch (MyException e) {
            throw e;
        }
        jsonArray.add(medicalHistory);



        HealthInfo healthInfo;
        try {
            healthInfo = healthInfoService.findByPersonId(personId);
        } catch (NoDataException e) {
            healthInfo = null;
        } catch (MyException e) {
            throw e;
        }
        jsonArray.add(healthInfo);


        SpecialityCheckup specialityCheckup;
        try {
            specialityCheckup = specialityCheckupService.findByPersonId(personId);
        } catch (NoDataException e) {
            specialityCheckup = null;

        } catch (MyException e) {
            throw e;
        }
        jsonArray.add(specialityCheckup);


        LaboratoryCheckup laboratoryCheckup;
        try {
            laboratoryCheckup = laboratoryCheckupService.findByPersonId(personId);
        } catch (NoDataException e) {
            laboratoryCheckup = null;
        }
        jsonArray.add(laboratoryCheckup);


        ZjkMedicalHistory zjkMedicalHistory;
        try {
            zjkMedicalHistory = zjkMedicalHistoryService.findByPersonId(personId);
        } catch (NoDataException e) {
            zjkMedicalHistory = null;
        }
        jsonArray.add(zjkMedicalHistory);


        FaceBedCheckup faceBedCheckup;
        try {
            faceBedCheckup = faceBedCheckupService.findByPersonId(personId);
        } catch (NoDataException e) {
            faceBedCheckup = null;
        }
        jsonArray.add(faceBedCheckup);


        FaceCheck faceCheck;
        try {
            faceCheck = faceCheckService.findByPersonId(personId);
        } catch (NoDataException e) {
            faceCheck = null;
        }
        jsonArray.add(faceCheck);


        JointCheck jointCheck;
        try {
            jointCheck = jointCheckService.findByPersonId(personId);
        } catch (NoDataException e) {
            jointCheck = null;
        }
        jsonArray.add(jointCheck);
//        ClinicalExamination clinicalExamination;
//        try {
//            clinicalExamination = clinicalExaminationService.findByPersonId(personId);
//        } catch (NoDataException e) {
//            clinicalExamination = null;
//        }
//        jsonArray.add(clinicalExamination);

        return jsonArray;
    }
}
