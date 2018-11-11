package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.JointCheck;
import com.smallcase.lushuju.repository.JointCheckRepository;
import com.smallcase.lushuju.service.JointCheckService;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service.impl
 * date: 2018/11/1 0:30
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Service
public class JointCheckServiceImpl implements JointCheckService {
    @Autowired
    private JointCheckRepository repository;


    @Override
    public JointCheck findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<JointCheck> findAll() {
        return repository.findAll();
    }

    @Override
    public JointCheck save(JointCheck faceCheck) throws MyException {
        JointCheck result;
        try {
            result = repository.save(faceCheck);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }

    @Override
    public JointCheck findByPersonId(String personId) throws MyException, NoDataException {
        JointCheck faceCheck;
        try {
            faceCheck = repository.findByPersonId(personId);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (faceCheck == null) {
            throw new NoDataException("关节检查数据找不到");
        }
        return faceCheck;
    }

    //TODO 关节检查
    @Override
    public JointCheck edit(JointCheck faceCheck, String personId) throws MyException {
        return null;
    }
}
