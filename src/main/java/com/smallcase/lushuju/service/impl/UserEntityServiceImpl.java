package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.repository.UserEntityRepository;
import com.smallcase.lushuju.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/10/18 19:41
 */

@Service
public class UserEntityServiceImpl implements UserEntityService {


    @Autowired
    private UserEntityRepository repository;

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
}
