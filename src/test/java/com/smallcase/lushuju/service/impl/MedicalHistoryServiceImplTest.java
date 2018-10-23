package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.service.MedicalHistoryService;
import com.smallcase.lushuju.utils.Exception.MyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 19:15
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedicalHistoryServiceImplTest {
    @Autowired
    private MedicalHistoryService service;

    @Test
    public void findOne() {
        MedicalHistory one = service.findOne(1);
        System.out.println(one.toString());
        Assert.assertNotNull(one);
    }

    @Test
    public void findAll() {
        List<MedicalHistory> all = service.findAll();
        System.out.println(all.size());
        Assert.assertNotNull(all);
    }

    @Test
    public void save() throws MyException {
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setSystemIndex("A");
        medicalHistory.setFamilyHistory("没有任何毛病啊");
        medicalHistory.setHistoryInfo("以前也没有毛病啊");
        medicalHistory.setPresentInfo("现在也没病");
        MedicalHistory result = service.save(medicalHistory);
        Assert.assertNotNull(result);
    }
}