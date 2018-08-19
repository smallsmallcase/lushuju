package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.LaboraryCheckup;
import com.smallcase.lushuju.repository.LaboraryCheckupRepository;
import com.smallcase.lushuju.service.LaboraryCheckupService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    public ResponseEntity save(LaboraryCheckup laboraryCheckup) {
        try {
            LaboraryCheckup result = repository.save(laboraryCheckup);
            return RestfulResult.ok(result.getPersonId());


        } catch (DataIntegrityViolationException e) {
            return RestfulResult.serviceErr(0);
        }    }

    /**
     * 查询实验室及器械检查
     * @param personId
     * @return
     */
    @Override
    public LaboraryCheckup findByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }


    /**
     * 修改实验室及器械检查
     * @param form
     * @param personId
     * @throws MyException
     */
    @Override
    @Transactional
    public void edit(LaboraryCheckup form, String personId) throws MyException {
        LaboraryCheckup laboraryCheckup = repository.findByPersonId(personId);
        if (laboraryCheckup == null) {
            log.error("【查询实验室及器械检查】：数据找不到");
            throw new MyException("查询实验室及器械检查：数据找不到");
        }

        BeanUtil.copyPropertiesIgnoreNull(form, laboraryCheckup);
        LaboraryCheckup result = repository.save(laboraryCheckup);
        if (result == null) {
            log.error("【修改数据】:LaboraryCheckup，出错");
            throw new MyException("修改数据出错");
        }
    }
}
