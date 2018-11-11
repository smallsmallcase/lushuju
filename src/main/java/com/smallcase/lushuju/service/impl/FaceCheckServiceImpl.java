package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.FaceCheck;
import com.smallcase.lushuju.repository.FaceCheckRepository;
import com.smallcase.lushuju.service.FaceCheckService;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * package: com.smallcase.lushuju.service.impl
 * date: 2018/11/1 0:17
 *
 * @author smallcase
 * @since JDK 1.8
 */


@Service
public class FaceCheckServiceImpl implements FaceCheckService {

    @Autowired
    private FaceCheckRepository repository;


    @Override
    public FaceCheck findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<FaceCheck> findAll() {
        return repository.findAll();
    }

    @Override
    public FaceCheck save(FaceCheck faceCheck) throws MyException {
        FaceCheck result;
        try {
            result = repository.save(faceCheck);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }

    @Override
    public FaceCheck findByPersonId(String personId) throws MyException, NoDataException {
        FaceCheck faceCheck;
        try {
            faceCheck = repository.findByPersonId(personId);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (faceCheck == null) {
            throw new NoDataException("面部检查数据找不到");
        }
        return faceCheck;
    }



    //TODO 面部检查修改
    @Override
    public FaceCheck edit(FaceCheck faceCheck, String personId) throws MyException {
        return null;
    }
}
