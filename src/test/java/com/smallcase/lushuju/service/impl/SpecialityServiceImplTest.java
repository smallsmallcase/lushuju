package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.SpecialityCheckup;
import com.smallcase.lushuju.service.SpecialityCheckupService;
import com.smallcase.lushuju.utils.Exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 21:56
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpecialityServiceImplTest {
    @Resource
    private SpecialityCheckupService service;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() throws MyException {

        SpecialityCheckup specialityCheckup = new SpecialityCheckup();
        specialityCheckup.setMouthInside("嘴巴");
        specialityCheckup.setMouthOutside("外面的嘴巴");
        SpecialityCheckup result = service.save(specialityCheckup);
        log.info(result.toString());
        Assert.assertNotNull(result);
    }

    @Test
    public void specialTest(){
        RestTemplate client = restTemplateBuilder.build();
    }
}