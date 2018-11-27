package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.BigClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * package: com.smallcase.lushuju.repository
 * date: 2018/11/27 18:48
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface BigClassRepository extends JpaRepository<BigClass, Integer> {
    BigClass findByClassName(String className);
}
