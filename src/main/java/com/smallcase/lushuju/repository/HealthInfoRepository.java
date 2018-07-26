package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.HealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/18 19:50
 */
public interface HealthInfoRepository extends JpaRepository<HealthInfo,Integer> {
    HealthInfo findByPersonId(String personId);
}
