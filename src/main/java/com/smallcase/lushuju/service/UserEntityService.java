package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.UserEntity;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/10/18 19:33
 */
public interface UserEntityService {
    UserEntity register(String userName, String password) throws Exception;

}
