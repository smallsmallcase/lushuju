package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.ArrayListConverter;
import com.smallcase.lushuju.conventer.DateConverter;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/6/30 10:37
 */
@Data
@Entity
@DynamicUpdate

/**
 * 正畸科病史
 */
public class ZjkMedicalHistory implements Serializable{

    private static final long serialVersionUID = -407888547944953187L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    /**正畸治疗史*/
    private String treatHistory;

    /**鼻咽部疾病*/
    private String nasopharyngealDisease;

    /**喂养方式*/
    private String feedPattern;

    /**不良习惯*/
    private String badHadit;

    /**家属是否有正畸治疗史*/
    @Column(name = "family_is_treat")
    private String familyIsTreat;

    /**外键*/
//    @OneToOne(targetEntity = PersonInfo.class)
//    @JoinColumn(name = "fkey",referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)

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
