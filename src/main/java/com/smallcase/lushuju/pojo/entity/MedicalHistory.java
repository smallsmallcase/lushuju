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

/**
 * 病史
 */
public class MedicalHistory implements Serializable{
    private static final long serialVersionUID = 997904126747035384L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    /**现病史*/
    private String presentInfo;

    /**既往史*/
    private String historyInfo;

    /**系统回顾*/
    private String systemIndex;

    @Column(name = "system_index_a")
    private String systemIndexA;

    @Column(name = "system_index_b")
    private String systemIndexB;

    @Column(name = "system_index_c")
    private String systemIndexC;

    @Column(name = "system_index_d")
    private String systemIndexD;

    @Column(name = "system_index_e")
    private String systemIndexE;

    @Column(name = "system_index_f")
    private String systemIndexF;

    @Column(name = "system_index_g")
    private String systemIndexG;

    @Column(name = "system_index_h")
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
