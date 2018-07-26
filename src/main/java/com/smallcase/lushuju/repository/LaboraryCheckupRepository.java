package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.LaboraryCheckup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/28 22:37
 */
public interface LaboraryCheckupRepository extends JpaRepository<LaboraryCheckup,Integer> {
    LaboraryCheckup findByPersonId(String personId);
}
