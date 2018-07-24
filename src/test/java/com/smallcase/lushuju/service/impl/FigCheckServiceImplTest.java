package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.FigCheck;
import com.smallcase.lushuju.service.FigCheckService;
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
 * Date: Created in 2018/7/1 18:32
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class FigCheckServiceImplTest {

    @Resource
    private FigCheckService service;

    @Test
    public void findOne() {
        FigCheck one = service.findOne(1);
        Assert.assertNotNull(one);
        log.info(one.toString());
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {

        FigCheck figCheck = new FigCheck();
        figCheck.setFaceImg("faf11222.jpg");
        figCheck.setImgData("213131.jpg");
        FigCheck result = service.save(figCheck);
        Assert.assertNotNull(result);
        log.info(result.toString());
    }
}