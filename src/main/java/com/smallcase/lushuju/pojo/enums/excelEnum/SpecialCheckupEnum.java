package com.smallcase.lushuju.pojo.enums.excelEnum;
/**
 * @Author: smallcase
 * @Date: 2019/1/8 17:05
 * @Package com.smallcase.lushuju.pojo.enums.excelEnum
 */

import lombok.Getter;

/**
 * 专科检查
 */
@Getter
public enum SpecialCheckupEnum {
    MOUTHINSIDE("mouthInside", "口外"),
    MOUTHOUTSIDE("mouthOutSide","口外")
    ;

    private String engName;
    private String chName;

    SpecialCheckupEnum(String engName, String chName) {

        this.engName = engName;
        this.chName = chName;
    }}

