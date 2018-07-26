package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.ClinicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/30 11:20
 */
public interface ClinicalExaminationRepository extends JpaRepository<ClinicalExamination, Integer> {
    ClinicalExamination findByPersonId(String personId);
}
