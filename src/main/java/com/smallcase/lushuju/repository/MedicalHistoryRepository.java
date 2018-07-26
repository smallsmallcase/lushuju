package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/18 19:10
 */
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {
    MedicalHistory findByPersonId(String personId);
}
