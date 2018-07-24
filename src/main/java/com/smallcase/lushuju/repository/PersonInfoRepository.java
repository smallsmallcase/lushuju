package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/18 16:54
 */
public interface PersonInfoRepository extends JpaRepository<PersonInfo, String> {

}
