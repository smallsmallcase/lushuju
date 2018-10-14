package com.smallcase.lushuju.service.impl;

import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.repository.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by huangds on 2017/10/28.
 */
@Service
@Deprecated
public class LoginService {

    @Autowired
    private LoginDao loginDao;

  public boolean verifyLogin(UserEntity user){

     List<UserEntity> userList = loginDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
      return userList.size()>0;
  }

}
