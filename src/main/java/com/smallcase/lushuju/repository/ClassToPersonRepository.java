package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.ClassToPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * package: com.smallcase.lushuju.repository
 * date: 2018/11/27 23:45
 *
 * @author smallcase
 * @since JDK 1.8
 */
public interface ClassToPersonRepository extends JpaRepository<ClassToPerson, Integer> {
    List<ClassToPerson> findByPersonId(String personId);

    @Query(value = "SELECT * FROM class_to_person WHERE class_id=?1 LIMIT ?3,?2", nativeQuery = true)
    List<ClassToPerson> findPagesByClassId(Integer classId, int limit, int offsetValue);

    int countClassToPersonByClassId(Integer classId);



    @Query(value = "DELETE FROM class_to_person WHERE person_id=?1",nativeQuery = true)
    @Modifying
    int deleteSomePersonId(String personId);
}
