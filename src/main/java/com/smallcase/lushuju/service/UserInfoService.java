package com.smallcase.lushuju.service;

import com.smallcase.lushuju.pojo.entity.PersonInfo;
import com.smallcase.lushuju.pojo.entity.UserEntity;

import java.util.List;

/**
 * Package: com.smallcase.lushuju.service
 * Author: smallcase
 * Date: Created in 2018/7/4 14:55
 */
public interface UserInfoService {

    //通过用户名查找用户信息
    public UserEntity findByUsernameAndPassword(String username,String password);

    //通过用户名查找用户信息
    public UserEntity findByUsername(String userName) throws Exception;

    //注册
    public UserEntity register(String userName, String password) throws Exception;

    //通过userId，查询该用户录了哪些病人列表
    public List<PersonInfo> listPersonInfoByUser(Integer userId, int pageSize, int pageNum);

    //通过userid，查询该用户录取的病人个数
    int listPersonInfoNumByUserId(Integer userId);



}
