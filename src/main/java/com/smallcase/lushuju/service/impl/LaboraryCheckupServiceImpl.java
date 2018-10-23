package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.LaboraryCheckup;
import com.smallcase.lushuju.repository.LaboraryCheckupRepository;
import com.smallcase.lushuju.service.LaboraryCheckupService;
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
public class LaboraryCheckupServiceImpl implements LaboraryCheckupService {

    @Resource
    private LaboraryCheckupRepository repository;


    @Override
    public LaboraryCheckup findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<LaboraryCheckup> findAll() {
        return repository.findAll();
    }

    @Override
    public LaboraryCheckup save(LaboraryCheckup laboraryCheckup) throws MyException {
        LaboraryCheckup result;
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
    public LaboraryCheckup findByPersonId(String personId) throws MyException, NoDataException {
        LaboraryCheckup laboraryCheckup;
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
    public LaboraryCheckup edit(LaboraryCheckup form, String personId) throws MyException {
        LaboraryCheckup laboraryCheckup;
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
