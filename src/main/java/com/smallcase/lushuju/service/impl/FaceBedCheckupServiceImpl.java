package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import com.smallcase.lushuju.repository.FaceBedCheckupRepository;
import com.smallcase.lushuju.service.FaceBedCheckupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/7 16:24
 */

@Service
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
    public FaceBedCheckup save(FaceBedCheckup faceBedCheckup) {
        return repository.save(faceBedCheckup);
    }

}
