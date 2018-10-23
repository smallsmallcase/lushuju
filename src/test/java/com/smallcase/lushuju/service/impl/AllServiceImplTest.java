package com.smallcase.lushuju.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.smallcase.lushuju.service.AllService;
import com.smallcase.lushuju.utils.Exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * package: com.smallcase.lushuju.service.impl
 * date: 2018/10/23 17:19
 *
 * @author smallcase
 * @since JDK 1.8
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AllServiceImplTest {

    @Autowired
    private AllService service;

    @Test
    public void findAllInfoByPersonId() {
        JSONArray jsonArray = null;

        try {
            jsonArray = service.findAllInfoByPersonId("2c92657f6513797901651ebbcc270000");

        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println(jsonArray);

    }
}