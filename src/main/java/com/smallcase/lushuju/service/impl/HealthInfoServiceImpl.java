package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.HealthInfo;
import com.smallcase.lushuju.repository.HealthInfoRepository;
import com.smallcase.lushuju.service.HealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/6/18 19:54
 */

@Service
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

    @PostConstruct
    public void init() {
        System.out.println("this is service init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("this is service clear up");
    }
}
