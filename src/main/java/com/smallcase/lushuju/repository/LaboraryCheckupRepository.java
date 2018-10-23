package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.LaboratoryCheckup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/28 22:37
 */
public interface LaboraryCheckupRepository extends JpaRepository<LaboratoryCheckup,Integer> {
    LaboratoryCheckup findByPersonId(String personId);
}
