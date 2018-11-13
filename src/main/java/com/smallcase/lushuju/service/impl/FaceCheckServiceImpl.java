package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.FaceCheck;
import com.smallcase.lushuju.repository.FaceCheckRepository;
import com.smallcase.lushuju.service.FaceCheckService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    public boolean checkExisted(String personId) {
        FaceCheck faceCheck = repository.findByPersonId(personId);
        return faceCheck != null;
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



    @Override
    public FaceCheck edit(FaceCheck form, String personId) throws MyException {
        FaceCheck faceCheck;
        try {
            faceCheck = repository.findByPersonId(personId);
            if (faceCheck == null) {
                log.error("【修改面部检查】：数据找不到");
                throw new MyException("数据找不到");
            }
            BeanUtil.copyPropertiesIgnoreNull(form, faceCheck);
            faceCheck = repository.save(faceCheck);
            if (faceCheck == null) {
                log.error("【修改数据】:faceCheck，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        return faceCheck;
    }
}
