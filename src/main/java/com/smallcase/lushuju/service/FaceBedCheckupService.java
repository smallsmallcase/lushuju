package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/7 16:22
 */
public interface FaceBedCheckupService {
    FaceBedCheckup findOne(Integer id);

    List<FaceBedCheckup> findAll();

    FaceBedCheckup save(FaceBedCheckup faceBedCheckup);

}
