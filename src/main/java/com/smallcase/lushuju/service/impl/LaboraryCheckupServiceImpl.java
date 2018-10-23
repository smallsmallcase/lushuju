package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.LaboratoryCheckup;
import com.smallcase.lushuju.repository.LaboraryCheckupRepository;
import com.smallcase.lushuju.service.LaboratoryCheckupService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.Exception.MyException;
import com.smallcase.lushuju.utils.Exception.NoDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/28 22:42
 */

@Service
@Slf4j
public class LaboraryCheckupServiceImpl implements LaboratoryCheckupService {

    @Resource
    private LaboraryCheckupRepository repository;


    @Override
    public LaboratoryCheckup findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<LaboratoryCheckup> findAll() {
        return repository.findAll();
    }

    @Override
    public LaboratoryCheckup save(LaboratoryCheckup laboraryCheckup) throws MyException {
        LaboratoryCheckup result;
        try {
             result = repository.save(laboraryCheckup);
        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
        return result;
    }


    /**
     * 查询实验室及器械检查
     * @param personId
     * @return
     */
    @Override
    public LaboratoryCheckup findByPersonId(String personId) throws MyException, NoDataException {
        LaboratoryCheckup laboraryCheckup;
        try {
            laboraryCheckup = repository.findByPersonId(personId);

        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (laboraryCheckup == null) {
            throw new NoDataException("laboraryCheckup数据找不到");
        }
        return laboraryCheckup;
    }


    /**
     * 修改实验室及器械检查
     * @param form
     * @param personId
     * @throws MyException
     */
    @Override
    @Transactional
    public LaboratoryCheckup edit(LaboratoryCheckup form, String personId) throws MyException {
        LaboratoryCheckup laboraryCheckup;
        try {

            laboraryCheckup = repository.findByPersonId(personId);
            if (laboraryCheckup == null) {
                log.error("【查询实验室及器械检查】：数据找不到");
                throw new MyException("查询实验室及器械检查：数据找不到");
            }

            BeanUtil.copyPropertiesIgnoreNull(form, laboraryCheckup);
            laboraryCheckup = repository.save(laboraryCheckup);
            if (laboraryCheckup == null) {
                log.error("【修改数据】:LaboraryCheckup，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        return laboraryCheckup;
    }
}
