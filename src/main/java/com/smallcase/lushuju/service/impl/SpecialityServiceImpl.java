package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.SpecialityCheckup;
import com.smallcase.lushuju.repository.SpecialityCheckupRepository;
import com.smallcase.lushuju.service.SpecialityCheckupService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 21:54
 */

@Service
@Slf4j
public class SpecialityServiceImpl implements SpecialityCheckupService {

    @Autowired
    private SpecialityCheckupRepository repository;
    @Override
    public SpecialityCheckup findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<SpecialityCheckup> findAll() {
        return repository.findAll();
    }

    @Override
    public SpecialityCheckup save(SpecialityCheckup specialityCheckup) throws MyException {
        SpecialityCheckup result;
        try {
            result = repository.save(specialityCheckup);

        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }

        return result;
    }

    @Override
    public SpecialityCheckup findByPersonId(String personId) throws MyException, NoDataException {
        SpecialityCheckup specialityCheckup;
        try {
            specialityCheckup = repository.findbypid(personId);


        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        if (specialityCheckup == null) {
            throw new NoDataException("专科检查数据找不到");
        }
        return specialityCheckup;
    }

    @Override
    public SpecialityCheckup edit(SpecialityCheckup form, String personId) throws MyException{
        SpecialityCheckup specialityCheckup;
        try {
            specialityCheckup = repository.findbypid(personId);
            if (specialityCheckup == null) {
                log.error("【修改体格检查】：数据找不到");
                throw new MyException("数据找不到");
            }
            BeanUtil.copyPropertiesIgnoreNull(form, specialityCheckup);
            specialityCheckup = repository.save(specialityCheckup);
            if (specialityCheckup == null) {
                log.error("【修改数据】:SpecialityCheckup，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        return specialityCheckup;
    }
}
