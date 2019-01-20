package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * @author smallcase
 * @package: com.smallcase.lushuju.pojo.enums.excelEnum
 * @date: 2019/1/8 21:57
 * @since JDK 1.8
 */
@Getter
public enum ZjkMedicalHistoryEnum {
    TREATHISTORY("treatHistory", "正畸治疗史"),
    NASOPHARYNGEALDISEASE("nasopharyngealDisease", "鼻咽部疾病"),
    FEEDPATTERN("feedPattern", "喂养方式"),
    BADHIBIT("badHadit", "不良习惯"),
    FAMILYISTREAT("familyIsTreat","家属是否有正畸治疗史")


    ;
    private String enName;
    private String chName;

    ZjkMedicalHistoryEnum(String enName, String chName) {
        this.enName = enName;
        this.chName = chName;

    }
}
