package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
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
 * Date: Created in 2018/10/22 15:53
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserInfoServiceImplTest {

    @Autowired
    private UserInfoService service;

    @Test
    public void listPersonInfoByUser() {

        List<PersonInfo> personInfos = service.listPersonInfoByUser(1,2,3);
        System.out.println(personInfos);
        System.out.println(personInfos.size());

    }
}