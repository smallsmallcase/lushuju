package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.repository.UserEntityRepository;
import com.smallcase.lushuju.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package: com.smallcase.lushuju.service.impl
 * Author: smallcase
 * Date: Created in 2018/7/4 14:57
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserEntityRepository repository;
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

}
