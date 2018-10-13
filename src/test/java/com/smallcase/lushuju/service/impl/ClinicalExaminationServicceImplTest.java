package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.service.ClinicalExaminationService;
import com.smallcase.lushuju.utils.MyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/30 11:25
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ClinicalExaminationServicceImplTest {

    @Autowired
    private ClinicalExaminationService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
        ClinicalExamination clinicalExamination = new ClinicalExamination();
        clinicalExamination.setPersonId("2c9344c86552320701655232c9930000");
        clinicalExamination.setOpenAngle("okok12vaf");
        clinicalExamination.setFaceShapeSide("face脸");
        ResponseEntity result = null;
        try {
            result = service.save(clinicalExamination);
        } catch (MyException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
        log.info(result.toString());
    }
}