package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.SpecialityCheckup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/18 21:49
 */
public interface SpecialityCheckupRepository extends JpaRepository<SpecialityCheckup, Integer> {
   @Query(value = "SELECT * FROM speciality_checkup  WHERE person_id=:personId",nativeQuery = true)
    SpecialityCheckup findbypid(@Param("personId") String personId);

}
