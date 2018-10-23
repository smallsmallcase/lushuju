package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.LaboratoryCheckup;
import com.smallcase.lushuju.service.LaboratoryCheckupService;
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
 * Date: Created in 2018/6/28 22:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LaboraryCheckupServiceImplTest {

    @Resource
    private LaboratoryCheckupService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {

        LaboratoryCheckup laboraryCheckup = new LaboratoryCheckup();
        laboraryCheckup.setCt("CT111");
        laboraryCheckup.setMrc("MRC111");
        laboraryCheckup.setUltrasound("挥发发");

        LaboratoryCheckup result = null;
        try {
            result = service.save(laboraryCheckup);
        } catch (MyException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);

        log.info(result.toString());

    }
}