package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import com.smallcase.lushuju.repository.FaceBedCheckupRepository;
import com.smallcase.lushuju.service.FaceBedCheckupService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
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
    public ResponseEntity save(FaceBedCheckup faceBedCheckup) {
        try {
            FaceBedCheckup result = repository.save(faceBedCheckup);
            return RestfulResult.ok(result.getPersonId());


        } catch (DataIntegrityViolationException e) {
            return RestfulResult.serviceErr(0);
        }
    }

    /**
     * 查询临床检查数据
     * @param personId
     * @return
     */
    @Override
    public FaceBedCheckup findByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }

    /**
     * 修改临床检查数据
     * @param form
     * @param personId
     * @throws MyException
     */
    @Override
    @Transactional
    public void edit(FaceBedCheckup form, String personId) throws MyException{
        FaceBedCheckup faceBedCheckup = repository.findByPersonId(personId);
        if (faceBedCheckup == null) {
            log.error("【修改临床检查】：数据找不到");
            throw new MyException("数据找不到");
        }
        BeanUtil.copyPropertiesIgnoreNull(form, faceBedCheckup);
        FaceBedCheckup result = repository.save(faceBedCheckup);
        if (result == null) {
            log.error("【修改数据】:FaceBedCheckup，出错");
            throw new MyException("修改数据出错");
        }
    }

}
