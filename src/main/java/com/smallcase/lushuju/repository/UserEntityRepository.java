package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

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


}
