package com.smallcase.lushuju.repository;

import com.smallcase.lushuju.pojo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Created by huangds on 2017/10/28.
 */
@Repository
public interface LoginDao extends CrudRepository<UserEntity, Integer> {

  public List<UserEntity> findByUsernameAndPassword(String username, String password);
}
