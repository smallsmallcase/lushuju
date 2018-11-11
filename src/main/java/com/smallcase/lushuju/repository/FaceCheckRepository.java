package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.FaceCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * package: com.smallcase.lushuju.repository
 * date: 2018/10/31 20:35
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Repository
public interface FaceCheckRepository extends JpaRepository<FaceCheck, Integer> {

    FaceCheck findByPersonId(String personId);

}
