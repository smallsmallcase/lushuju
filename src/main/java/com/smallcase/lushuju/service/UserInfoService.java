package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.UserEntity;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/4 14:55
 */
public interface UserInfoService {

    //通过用户名查找用户信息
    public UserEntity findByUsernameAndPassword(String username,String password);

}
