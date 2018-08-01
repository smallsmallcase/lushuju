package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.DateConverter;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/6/18 18:59
 */

@Data
@DynamicUpdate
@Entity(name = "medical_history")
public class MedicalHistory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**现病史*/
    private String presentInfo;

    /**既往史*/
    private String historyInfo;

    /**系统回顾*/
    private String systemIndex;

    private String systemIndexA;

    private String systemIndexB;

    private String systemIndexC;

    private String systemIndexD;

    private String systemIndexE;

    private String systemIndexF;

    private String systemIndexG;

    private String systemIndexH;

    /**个人史*/
    private String personalHistory;

    /**家族史*/
    private String familyHistory;

    /**外键*/
//    @OneToOne(targetEntity = PersonInfo.class)
//    @JoinColumn(name = "fkey",referencedColumnName = "id")
//    @Column(name = "fkey")
    private String personId;

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
