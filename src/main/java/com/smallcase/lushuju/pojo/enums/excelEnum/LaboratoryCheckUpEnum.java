package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * @author smallcase
 * @package: com.smallcase.lushuju.pojo.enums.excelEnum
 * @date: 2019/1/8 21:46
 * @since JDK 1.8
 */
@Getter
public enum LaboratoryCheckUpEnum {
    MRC("mrc", "MRI"),
    CT("ct", "CT"),
    ULTRASOUND("ultrasound", "超声"),
    PATHOLOGY("pathology","病理")
    ;

    private String enName;
    private String chName;

    LaboratoryCheckUpEnum(String enName, String chName) {
        this.enName = enName;
        this.chName = chName;
    }}
