package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.ClassToPerson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * package: com.smallcase.lushuju.repository
 * date: 2018/11/28 19:53
 *
 * @author smallcase
 * @since JDK 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassToPersonRepositoryTest {

    @Autowired
    private ClassToPersonRepository repository;

    @Test
    public void findByPersonId() {
        String personId = "2c92657f6513797901651ebbcc270000";
        List<ClassToPerson> byPersonId = repository.findByPersonId(personId);
        Assert.assertEquals(2, byPersonId.size());
    }

    @Test
    public void findByClassId() {
        Integer classId = 2;
        List<ClassToPerson> byClassId = repository.findByClassId(2);
        Assert.assertEquals(1, byClassId.size());
    }

    @Test
    @Transactional
    public void deleteAllByPersonId() {
        String personId = "2c92657f6513797901651ebbcc270000";
        int effectedNum = repository.deleteSomePersonId(personId);
        System.out.println(effectedNum);
        Assert.assertEquals(2, effectedNum);
    }
}