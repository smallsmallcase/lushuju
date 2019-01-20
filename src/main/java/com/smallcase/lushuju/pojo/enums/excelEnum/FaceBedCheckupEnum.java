package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * package: com.smallcase.lushuju.pojo.enums.excelEnum
 * date: 2019/1/18 13:28
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Getter
public enum FaceBedCheckupEnum {
    RUYAHE("ruyahe", "乳牙合情况"),
    HENYAHE("henyahe", "恒牙合情况"),
    UPTOOTH("up_tooth", "上牙弓"),
    DOWNTOOTH("down_tooth", "下牙弓")
    ;

    private String enName;
    private String chName;

    FaceBedCheckupEnum(String enName, String chName) {
        this.enName = enName;
        this.chName = chName;
    }}
