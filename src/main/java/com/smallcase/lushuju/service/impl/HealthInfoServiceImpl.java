package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.HealthInfo;
import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import com.smallcase.lushuju.repository.HealthInfoRepository;
import com.smallcase.lushuju.service.HealthInfoService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import com.smallcase.lushuju.utils.RestfulResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 19:54
 */

@Service
@Slf4j
public class HealthInfoServiceImpl implements HealthInfoService {

    @Autowired
    private HealthInfoRepository repository;
    @Override
    public HealthInfo findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<HealthInfo> findAll() {
        return repository.findAll();
    }



    @Override
    public HealthInfo save(HealthInfo healthInfo) throws MyException {
        try {
            return repository.save(healthInfo);


        } catch (DataIntegrityViolationException e) {
            throw new MyException(e.getMessage());
        }
    }


    /**
     * 查询体格检查
     * @param personId
     * @return
     */
    @Override
    public HealthInfo findByPersonId(String personId) throws MyException {
        HealthInfo healthInfo;
        try {
            healthInfo = repository.findByPersonId(personId);
            if (healthInfo == null) {
                throw new MyException("healthInfo信息找不到");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }
        return healthInfo;
    }

    /**
     * 修改体格检查
     * @param form
     * @param personId
     */
    @Override
    @Transactional
    public HealthInfo edit(HealthInfo form, String personId) throws MyException {
        HealthInfo healthInfo;
        try {
            healthInfo = repository.findByPersonId(personId);
            if (healthInfo == null) {
                log.error("【修改体格检查】：数据找不到");
                throw new MyException("数据找不到");
            }
            BeanUtil.copyPropertiesIgnoreNull(form, healthInfo);
            healthInfo = repository.save(healthInfo);
            if (healthInfo == null) {
                log.error("【修改数据】:HealthInfo，出错");
                throw new MyException("修改数据出错");
            }
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        return healthInfo;
    }

    @PostConstruct
    public void init() {
        System.out.println("this is service init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("this is service clear up");
    }
}
