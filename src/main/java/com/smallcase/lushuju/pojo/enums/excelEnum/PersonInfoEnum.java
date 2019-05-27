package com.smallcase.lushuju.pojo.enums.excelEnum;

import lombok.Getter;

/**
 * @Author: smallcase
 * @Date: 2019/1/3 15:13
 * @Package com.smallcase.lushuju.pojo.enums.excelEnum
 */
@Getter
public enum PersonInfoEnum {
    NAME("personName", "姓名"),
    SEX("personSex", "性别"),
    AGE("personAge", "年龄"),
    PATIENTID("patientId","病人ID"),
    MARRIAGE("personMarriage", "婚姻"),
    BIRTHPLACE("personBirthplace", "出生地"),
    NATION("personNation", "名族"),
    CAREER("personCareer", "职业"),
    WORKPLACE("personWorkplace", "工作单位"),
    HOME("personHome", "住址"),
    SPEAKER("personSpeaker", "供史者"),
    IN("personIn", "入院时间"),
    OUT("personOut", "记录时间"),
    TEXT("personText", "主述"),

    ;

    private String engName;
    private String chName;

    PersonInfoEnum(String engName, String chName) {
        this.engName = engName;
        this.chName = chName;
    }}
