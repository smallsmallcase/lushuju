package com.smallcase.lushuju.pojo.entity;

/**
 * package: com.smallcase.lushuju.pojo.entity
 * date: 2018/11/12 10:47
 *
 * @author smallcase
 * @since JDK 1.8
 */


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 角色信息表
 */

@Data
@Entity(name = "role")
public class Role {

    @Id
    private Integer id;

    private Integer roleId;

    private String roleDesc;

}
