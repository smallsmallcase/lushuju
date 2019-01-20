package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * package: com.smallcase.lushuju.pojo.enums.excelEnum
 * date: 2019/1/18 13:49
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Getter
public enum JointCheckEnum {
    OPENTYPE("openType", "开口型"),
    OPENANGLE("openAngle", "开口度"),
    SNAP("snap", "弹响"),
    ACHE("ache","疼痛")
    ;

    private String enName;
    private String chName;

    JointCheckEnum(String enName, String chName) {
        this.enName = enName;
        this.chName = chName;
    }}
