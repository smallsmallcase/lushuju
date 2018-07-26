package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.FaceBedCheckup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/7/7 16:21
 */
public interface FaceBedCheckupRepository extends JpaRepository<FaceBedCheckup, Integer> {
    FaceBedCheckup findByPersonId(String personId);
}
