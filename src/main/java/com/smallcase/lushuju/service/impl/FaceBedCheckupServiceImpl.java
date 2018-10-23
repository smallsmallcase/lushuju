package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import com.smallcase.lushuju.repository.FaceBedCheckupRepository;
import com.smallcase.lushuju.service.FaceBedCheckupService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/7 16:24
 */

@Service
@Slf4j
public class FaceBedCheckupServiceImpl implements FaceBedCheckupService {

    @Autowired
    private FaceBedCheckupRepository repository;

    @Override
    public FaceBedCheckup findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<FaceBedCheckup> findAll() {
        return repository.findAll();
    }

    @Override
    public FaceBedCheckup save(FaceBedCheckup faceBedCheckup) throws MyException {
        try {
            FaceBedCheckup result = repository.save(faceBedCheckup);
            if (result == null) {
                throw new MyException("FaceBedCheckup录入出错");
            } else {
                return result;

            }

        } catch (DataIntegrityViolationException e) {
            throw new MyException("FaceBedCheckup录入出错");
        }
    }

    /**
     * 查询临床检查数据
     * @param personId
     * @return
     */
    @Override
    public FaceBedCheckup findByPersonId(String personId) throws MyException, NoDataException {
        FaceBedCheckup faceBedCheckup = null;
        try {
            faceBedCheckup = repository.findByPersonId(personId);
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (faceBedCheckup == null) {
            throw new NoDataException("FaceBedCheckup数据找不到");
        }

        return faceBedCheckup;

    }

    /**
     * 修改临床检查数据
     * @param form
     * @param personId
     * @throws MyException
     */
    @Override
    @Transactional
    public FaceBedCheckup edit(FaceBedCheckup form, String personId) throws MyException{
        FaceBedCheckup faceBedCheckup;
        try {
            faceBedCheckup = repository.findByPersonId(personId);
            if (faceBedCheckup == null) {
                log.error("【修改临床检查】：数据找不到");
                throw new MyException("数据找不到");
            }
            BeanUtil.copyPropertiesIgnoreNull(form, faceBedCheckup);
            faceBedCheckup = repository.save(faceBedCheckup);
            if (faceBedCheckup == null) {
                log.error("【修改数据】:FaceBedCheckup，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException("修改数据出错");
        }
        return faceBedCheckup;
    }

}
