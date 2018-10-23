package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import com.smallcase.lushuju.service.ZjkMedicalHistoryService;
import com.smallcase.lushuju.utils.Exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/30 10:47
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ZjkMedicalHistoryServiceImplTest {

    @Resource
    private ZjkMedicalHistoryService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() throws MyException {

        ZjkMedicalHistory zjkMedicalHistory = new ZjkMedicalHistory();
        zjkMedicalHistory.setBadHadit("习惯不好啊");
        zjkMedicalHistory.setFeedPattern("这么喂养的");
        zjkMedicalHistory.setTreatHistory("以前治疗过");
        zjkMedicalHistory.setFamilyIsTreat("家人被治疗过");
        ZjkMedicalHistory result = service.save(zjkMedicalHistory);
        Assert.assertNotNull(result);
        log.info(result.toString());
    }
}