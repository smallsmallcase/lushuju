package com.smallcase.lushuju.pojo.enums;

import lombok.Getter;

/**
 * package: com.smallcase.lushuju.pojo.enums
 * date: 2018/11/12 10:57
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Getter
public enum RoleEnum {

    ADMIN(0,"管理员"),
    GUEST(1,"普通用户"),
    NOOAUTH(2,"零权限用户");


    private Integer roleId;
    private String roleDesc;

    RoleEnum(Integer roleId, String roleDesc) {
        this.roleDesc = roleDesc;
        this.roleId = roleId;
    }
}
