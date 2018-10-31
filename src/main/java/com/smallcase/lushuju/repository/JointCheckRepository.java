package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.JointCheck;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * package: com.smallcase.lushuju.repository
 * date: 2018/11/1 0:08
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface JointCheckRepository extends JpaRepository<JointCheck, Integer> {
    JointCheck findByPersonId(String personId);
}
