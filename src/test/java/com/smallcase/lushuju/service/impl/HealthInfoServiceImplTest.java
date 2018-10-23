package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.HealthInfo;
import com.smallcase.lushuju.service.HealthInfoService;
import com.smallcase.lushuju.utils.Exception.MyException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 19:57
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthInfoServiceImplTest {

    @Autowired
    private HealthInfoService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() throws MyException {
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.setHeight("167");
        HealthInfo result = service.save(healthInfo);
        Assert.assertNotNull(result);
    }
}