package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.Detail;
import com.smallcase.lushuju.service.DetailService;
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
 * Date: Created in 2018/7/1 18:49
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DetailServiceImplTest {

    @Resource
    private DetailService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {

        Detail detail = new Detail();
        detail.setGroupName("1号组");
        Detail result = service.save(detail);
        Assert.assertNotNull(result);
        log.info(result.toString());
    }
}