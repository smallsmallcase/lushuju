package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.repository.PersonInfoRepository;
import com.smallcase.lushuju.repository.UserEntityRepository;
import com.smallcase.lushuju.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/4 14:57
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserEntityRepository repository;

    @Autowired
    private PersonInfoRepository personInfoRepository;
//    @Override
//    public UserEntity findByUsername(String username) {
//
//        System.out.println("UserInfoServiceImpl.findByUsername()");
//        return repository.findByUsername(username);
//    }

    @Override
    public UserEntity findByUsernameAndPassword(String username, String password) {

        return repository.findByUsernameAndPassword(username, password);
    }

    @Override
    public UserEntity findByUsername(String userName) throws Exception {
        UserEntity userEntity = repository.findByUsername(userName);
        if (userEntity == null) {
            throw new Exception("用户信息找不到");
        }
        return userEntity;

    }

    @Override
    @Transactional
    public UserEntity register(String userName, String password) throws Exception {
        UserEntity userEntity;
        try {
            userEntity = repository.findByUsername(userName);
            if (userEntity != null) {
                throw new Exception("该用户名已经被注册");
            } else {
                userEntity = new UserEntity();
                userEntity.setUsername(userName);
                userEntity.setPassword(password);

                userEntity = repository.save(userEntity);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return userEntity;
    }


    /**
     * 查询该用户录了哪些病例
     *
     * @param userId
     * @return
     */
    @Override
    public List<PersonInfo> listPersonInfoByUser(Integer userId, int pageSize, int pageNum) {


        int offSetValue = calculatePage(pageSize, pageNum);
        return personInfoRepository.findPersonInfosByUserId(userId, pageSize, offSetValue);
    }

    @Override
    public int listPersonInfoNumByUserId(Integer userId) {
        return personInfoRepository.findPersonInfosNumByUserId(userId);
    }


    private int calculatePage(int pageSize, int pageNum) {
        return (pageNum - 1) * pageSize;
    }

}
