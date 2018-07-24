package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.Analysis;
import com.smallcase.lushuju.service.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/1 19:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AnalysisServiceImplTest {

    @Resource
    private AnalysisService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
        Analysis analysis = new Analysis();
        analysis.setAnshi("我是按时啊");
        Analysis result = service.save(analysis);
        Assert.assertNotNull(result);
        log.info(result.toString());
    }
}