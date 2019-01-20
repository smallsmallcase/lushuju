package com.smallcase.lushuju.pojo.enums.excelEnum;

/**
 * @Author: smallcase
 * @Date: 2019/1/8 15:40
 * @Package com.smallcase.lushuju.pojo.enums.excelEnum
 */

import lombok.Getter;

/**
 * 体格检查
 */
@Getter
public enum HealthInfoEnum {
    TEMPERATURE("temperature", "体温"),
    PULSE("pulse", "脉搏"),
    BREATHE("breathe", "呼吸"),
    BLOODPRESSURE("bloodPressure", "血压"),
    HEIGHT("height", "身高"),
    WEIGHT("weight", "体重"),
    GENERALCASE("generalCase", "一般情况"),
    SKIN("skin", "皮肤、粘膜"),
    LYMPHGLAND("lymphGland", "淋巴结"),
    HEADORGAN("headOrgan", "头部及其器官"),
    EYES("eyes", "眼"),
    EAR("ear", "耳"),
    NOSE("nose", "鼻"),
    MOUTH("mouth", "口腔"),
    NECK("neck", "颈部"),
    CHEST("chest", "胸部"),
    LUNG("lung", "肺部（视触叩听）"),
    HEART("heart", "心脏（视触叩听）"),
    ARTERY("artery", "桡动脉"),
    AROUND("around", "周围血管征"),
    BELLY("belly", "腹部（视触叩听）"),
    DOOR("door", "肛门、直肠及外生殖器"),
    SPINE("spine", "脊柱"),
    LIMBS("limbs", "四肢"),
    REFECT("reflect", "神经反射")


    ;

    private String engName;
    private String chName;

    HealthInfoEnum(String engName, String chName) {
        this.engName = engName;
        this.chName = chName;
    }}
