package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.BigClass;
import com.smallcase.lushuju.repository.BigClassRepository;
import com.smallcase.lushuju.service.BigClassService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service.impl
 * date: 2018/11/27 19:20
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Service
@Slf4j
public class BigClassServiceImpl implements BigClassService {
    private final BigClassRepository repository;

    @Autowired
    public BigClassServiceImpl(BigClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public BigClass insertBigClass(BigClass bigClass) {
        BigClass result = repository.save(bigClass);
        if (result == null) {
            throw  new RuntimeException("BigClass数据插入失败");
        }
        return result;
    }

    @Override
    public BigClass findBigClassById(Integer id) {
        BigClass one = repository.findOne(id);
        if (one == null) {
            throw new RuntimeException("BigClass数据找不到");
        }
        return one;

    }

    @Override
    public BigClass findBigClassByName(String className) throws MyException {
        BigClass bigClass = repository.findByClassName(className);
        if (bigClass == null) {
            throw new MyException("bigClass查不到");
        }
        return bigClass;

    }

    @Override
    public boolean checkExist(String className) {
        BigClass one = repository.findByClassName(className);
        /*
        如果有，就是已经录入了，返回false
         */
        if (one != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public BigClass edit(BigClass form) throws MyException {
        BigClass bigClass;
        try {
            bigClass = repository.findByClassName(form.getClassName());
            BeanUtil.copyPropertiesIgnoreNull(form, bigClass);
            bigClass = repository.save(bigClass);
            if (bigClass == null) {
                log.error("【修改数据】:PersonInfo，出错");
                throw new MyException("修改数据出错");
            }

        } catch (RuntimeException e) {
            throw new MyException(e.getMessage());
        }
        return bigClass;
    }

    @Override
    public List<BigClass> findAll() {
        List<BigClass> all = repository.findAll();
        if (all.size() == 0) {
            throw new RuntimeException("BigClass数据找不到");
        }
        return all;
    }

    @Override
    public void deleteOne(Integer id) {
        try {
            repository.delete(id);

        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
