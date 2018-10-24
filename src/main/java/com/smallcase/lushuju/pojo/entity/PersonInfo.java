package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.DateConverter;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/6/18 16:08
 */

/**
 * 个人信息表
 */
@Data
@Entity(name = "person_info")
@DynamicUpdate
public class PersonInfo implements Serializable {

    private static final long serialVersionUID = -713320137148990258L;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid32")
    @GenericGenerator(name = "uuid32", strategy = "uuid")
    @Column(name = "id")
    private String personId;

    /**名字*/
    @NotBlank
    private String personName;

    /**性别*/
    @NotBlank
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
    private String personRecord;

//    /**身份证*/
//    private String personIdcard;
//
//    /**联系方式*/
//    private String personPhone;

    /**主诉*/
    private String personText;

    /**用户（录数据的人）*/
    private Integer userId;

    /**图片地址*/
    @JsonIgnore
    private String imgPath;

    /**
     * 图片名字
     */
    @JsonIgnore
    private String imgName;

    /**
     * 创建时间
     */
    @Column(updatable = false)
    @Convert(converter = DateConverter.class)
    @JsonIgnore
    private Long createTime = System.currentTimeMillis();

    /**修改时间*/
    @JsonIgnore
    @Convert(converter = DateConverter.class)
    private Long updateTime = System.currentTimeMillis();

}
