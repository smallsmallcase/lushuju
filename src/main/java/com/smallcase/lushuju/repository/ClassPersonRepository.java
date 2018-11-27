package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.ClassToPerson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * package: com.smallcase.lushuju.repository
 * date: 2018/11/27 19:16
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface ClassPersonRepository extends JpaRepository<ClassToPerson, Integer> {

}
