package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.repository
 * Author: smallcase
 * Date: Created in 2018/7/2 16:57
 */
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * 通过username和password查找用户信息
     * @param username
     * @param password
     * @return
     */
    public UserEntity findByUsernameAndPassword(String username, String password);

    /**
     * 通过用户名查找
     * @param username
     * @return
     */
    public UserEntity findByUsername(String username);


    /**
     * 更改密码
     * @param userName
     * @param newPassword
     * @return
     */
    @Modifying
    @Query(value = "UPDATE user_entity SET password=:password WHERE username=:userName",nativeQuery = true)
    int changepwd(@Param("userName") String userName, @Param("password") String newPassword);


    /**
     * 更改user的使能状态
     * @param targetStatus
     * @param userId
     * @return
     */
    @Modifying
    @Query(value = "UPDATE user_entity SET enable_status=:targetStatus WHERE id=:userId",nativeQuery = true)
    int changeStatus(@Param("targetStatus") Integer targetStatus, @Param("userId") Integer userId);


}
