package com.smallcase.lushuju.pojo.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Package: com.smallcase.lushuju.pojo.form
 * Author: smallcase
 * Date: Created in 2018/7/8 15:21
 */
@Data
public class PersonInfoForm {

    /**名字*/
    @NotNull
    private String personName;

    /**性别*/
    @NotNull
    private String personSex;

    /**年龄*/
    private Integer personAge;

    /**婚姻*/
    private String personMarriage;

    /**出生地*/
    private String personBirthplace;

    /**名族*/
    private String personNation;

    /**职业*/
    private String personCareer;

    /**工作单位*/
    private String personWorkplace;

    /**住址*/
    private String personHome;

    /**供史者*/
    private String personSpeak;

    /**入院时间*/
    private String personIn;

    /**记录时间*/
    private String personOut;

    /**身份证*/
    private String personIdcard;

    /**联系方式*/
    private String personPhone;

    /**主诉*/
    private String personText;
}
