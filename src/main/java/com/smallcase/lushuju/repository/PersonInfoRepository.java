package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/6/18 16:54
 */
public interface PersonInfoRepository extends JpaRepository<PersonInfo, String> {

    @Query(value ="SELECT * FROM person_info WHERE user_id=?1 LIMIT ?3,?2", nativeQuery = true)
    List<PersonInfo> findPersonInfosByUserId(Integer userId, int limit, int offSet);

    @Query(value ="SELECT * FROM person_info LIMIT ?2,?1", nativeQuery = true)
    List<PersonInfo> findPersonInfos(int limit, int offSet);


    @Query(value = "SELECT COUNT(*) FROM person_info WHERE user_id=?1",nativeQuery = true)
    int findPersonInfosNumByUserId(Integer userId);

    @Query(value = "SELECT COUNT(*) FROM person_info",nativeQuery = true)
    int countAll();

    @Query(value = "UPDATE person_info SET file_path=:imgPath,file_name=:fileName WHERE id=:id", nativeQuery = true)
    @Modifying
    int insertImgPath(@Param("imgPath") String imgPath, @Param("fileName")String fileName,@Param("id") String personId);

    @Query(value = "SELECT file_path FROM person_info WHERE id=:id",nativeQuery = true)
    String getImgPathByPersonId(@Param("id") String personId);


    @Query(value = "SELECT file_name FROM person_info WHERE id=:id",nativeQuery = true)
    String getImgNameByPersonId(@Param("id") String personId);

    List<PersonInfo> findByPatientId(String patientId);

    @Query(value = "UPDATE person_info SET file_path=:filePath WHERE id=:id",nativeQuery = true)
    @Modifying
    int insertFilePath(@Param("filePath") String filePath, @Param("id") String personId);

    @Query(value = "SELECT file_path FROM person_info WHERE id=?1",nativeQuery = true)
    String getFilePathByPersonId(String personId);

    @Query(value = "SELECT id FROM person_info WHERE" +
            " CASE ?4 WHEN IS NOT NULL THEN AND patient_id=?4 END " +
            "CASE ?1 WHEN IS NOT NULL THEN AND person_sex=?1 END " +
            "CASE ?2 WHEN IS NOT NULL THEN AND person_age>?2 END " +
            "CASE ?3 WHEN IS NOT NULL THEN AND person_age<?3 ",
            nativeQuery = true)
    List<String> findPersonIds(String sex, Integer minAge, Integer maxAge, String patientId);

    @Query(value = "SELECT id FROM person_info ",nativeQuery = true)
    List<String> findPersonIds();

    @Query(value = "SELECT id FROM person_info WHERE patient_id=?1",nativeQuery = true)
    List<String> findPersonIdsByPatientId(String patientId);

    @Query(value = "SELECT id FROM person_info WHERE person_age>=?1",nativeQuery = true)
    List<String> findPersonIdsByAge1(Integer minAge);

    @Query(value = "SELECT id FROM person_info WHERE person_age<=?1 ",nativeQuery = true)
    List<String> findPersonIdsByAge2(Integer maxAge);

    @Query(value = "SELECT id FROM person_info WHERE person_age>=?1 AND person_age<=?2 ",nativeQuery = true)
    List<String> findPersonIdsByAge3(Integer minAge,Integer maxAge);

    @Query(value = "SELECT id FROM person_info WHERE person_sex=?1 ",nativeQuery = true)
    List<String> findPersonIdsBySex(String sex);





}
