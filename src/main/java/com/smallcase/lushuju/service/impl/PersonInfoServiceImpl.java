package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.form.PersonInfoForm;
import com.smallcase.lushuju.repository.PersonInfoRepository;
import com.smallcase.lushuju.service.PersonInfoService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 17:04
 */
@Slf4j
@Service
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoRepository repository;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public PersonInfo findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<PersonInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity save(PersonInfo personInfo){
        try {
            PersonInfo result = repository.save(personInfo);
            return RestfulResult.ok(result.getId());


        } catch (DataIntegrityViolationException e) {
            return RestfulResult.serviceErr(0);
        }
    }


    @Override
    @Transactional
    public void edit(PersonInfoForm form, String personId) throws MyException {
        PersonInfo personInfo = repository.findOne(personId);
        BeanUtil.copyPropertiesIgnoreNull(form,personInfo);
        PersonInfo result = repository.save(personInfo);
        if (result == null) {
            log.error("【修改数据】:PersonInfo，出错");
            throw new MyException("修改数据出错");
        }
    }
}
