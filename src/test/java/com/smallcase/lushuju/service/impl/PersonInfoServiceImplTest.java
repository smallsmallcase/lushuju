package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.service.PersonInfoService;
import com.smallcase.lushuju.utils.MyException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 17:13
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PersonInfoServiceImplTest {

    @Autowired
    private PersonInfoService service;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
        List<PersonInfo> all = service.findAll();
//        log.info("{},{},{}","11","22","33");
        Assert.assertNotNull(all);
    }

    @Test
    public void save() throws MyException {

        PersonInfo personInfo = new PersonInfo();
        personInfo.setPersonAge(23);
        personInfo.setPersonName("胡林");
        personInfo.setPersonSex("男");

        service.save(personInfo);
    }
}