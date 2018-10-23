package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/18 16:54
 */
public interface PersonInfoRepository extends JpaRepository<PersonInfo, String> {

    @Query(value ="SELECT * FROM person_info WHERE user_id=?1 LIMIT ?3,?2", nativeQuery = true)
    List<PersonInfo> findPersonInfosByUserId(Integer userId, int limit, int offSet);


    @Query(value = "SELECT COUNT(*) FROM person_info WHERE user_id=?1",nativeQuery = true)
    int findPersonInfosNumByUserId(Integer userId);
}
