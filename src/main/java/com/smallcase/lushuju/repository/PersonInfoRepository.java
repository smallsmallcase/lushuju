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

    @Query(value = "UPDATE person_info SET img_path=:imgPath,img_name=:fileName WHERE id=:id", nativeQuery = true)
    @Modifying
    int insertImgPath(@Param("imgPath") String imgPath,@Param("fileName")String fileName, @Param("id") String personId);

    @Query(value = "SELECT img_path FROM person_info WHERE id=:id",nativeQuery = true)
    String getImgPathByPersonId(@Param("id") String personId);


    @Query(value = "SELECT img_name FROM person_info WHERE id=:id",nativeQuery = true)
    String getImgNameByPersonId(@Param("id") String personId);

    List<PersonInfo> findByPatientId(String patientId);

    @Query(value = "UPDATE person_info SET file_path=:filePath WHERE id=:id",nativeQuery = true)
    @Modifying
    int insertFilePath(@Param("filePath") String filePath, @Param("id") String personId);

    @Query(value = "SELECT file_path FROM person_info WHERE id=?1",nativeQuery = true)
    String getFilePathByPersonId(String personId);

}
