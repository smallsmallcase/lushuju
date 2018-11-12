package com.smallcase.lushuju.pojo.enums;

import lombok.Getter;

/**
 * package: com.smallcase.lushuju.pojo.enums
 * date: 2018/11/12 11:04
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Getter
public enum EnableStatusEnum {

    NO(0, "没有被授权"),
    YES(1, "已经被授权")

    ;

    private Integer enableStatus;
    private String StatusDesc;

    EnableStatusEnum(Integer enableStatus, String statusDesc) {
        this.enableStatus = enableStatus;
        StatusDesc = statusDesc;
    }
}
