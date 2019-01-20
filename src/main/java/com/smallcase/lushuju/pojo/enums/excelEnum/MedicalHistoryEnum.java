package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * @Author: smallcase
 * @Date: 2019/1/3 16:07
 * @Package com.smallcase.lushuju.pojo.enums.excelEnum
 */

@Getter
public enum MedicalHistoryEnum {
    PRESENTINFO("presentInfo", "现病史"),
    HISTORYINFO("historyInfo", "既往史"),
    SYSTEMINDEX("systemIndex", "系统回顾"),
    SYSTEMINDEXA("systemIndexA", "A呼吸系统"),
    SYSTEMINDEXB("systemIndexB", "B循环系统"),
    SYSTEMINDEXC("systemIndexC", "C消化系统"),
    SYSTEMINDEXD("systemIndexD", "D泌尿系统"),
    SYSTEMINDEXE("systemIndexE", "E造血系统"),
    SYSTEMINDEXF("systemIndexF", "内分泌系统记代谢"),
    SYSTEMINDEXG("systemIndexG", "G神经精神系统"),
    SYSTEMINDEXH("systemIndexH", "H肌肉骨骼系统"),
    PERSONHISTORY("personalHistory", "个人历史"),
    FAMILYHISTORY("familyHistory","家族史")

    ;


    private String engName;
    private String chName;

    MedicalHistoryEnum(String engName, String chName) {
        this.engName = engName;
        this.chName = chName;
    }}
