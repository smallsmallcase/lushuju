package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.HealthInfo;
import com.smallcase.lushuju.repository.HealthInfoRepository;
import com.smallcase.lushuju.service.HealthInfoService;
import com.smallcase.lushuju.utils.BeanUtil;
import com.smallcase.lushuju.utils.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    public HealthInfo save(HealthInfo healthInfo) {
        return repository.save(healthInfo);
    }


    /**
     * 查询体格检查
     * @param personId
     * @return
     */
    @Override
    public HealthInfo findByPersonId(String personId) {
        return repository.findByPersonId(personId);
    }

    /**
     * 修改体格检查
     * @param form
     * @param personId
     */
    @Override
    @Transactional
    public void edit(HealthInfo form, String personId) throws MyException {
        HealthInfo healthInfo = repository.findByPersonId(personId);
        if (healthInfo == null) {
            log.error("【修改体格检查】：数据找不到");
            throw new MyException("数据找不到");
        }
        BeanUtil.copyPropertiesIgnoreNull(form, healthInfo);
        HealthInfo result = repository.save(healthInfo);
        if (result == null) {
            log.error("【修改数据】:HealthInfo，出错");
            throw new MyException("修改数据出错");
        }
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
