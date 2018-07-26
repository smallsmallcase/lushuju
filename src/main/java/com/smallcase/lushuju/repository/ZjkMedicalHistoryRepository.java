package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.ZjkMedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/30 10:43
 */
public interface ZjkMedicalHistoryRepository extends JpaRepository<ZjkMedicalHistory, Integer> {
    ZjkMedicalHistory findByPersonId(String personId);
}
