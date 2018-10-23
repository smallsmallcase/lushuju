package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.DateConverter;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/6/28 22:28
 */

@Data
@Entity(name = "laborary_checkup")
@DynamicUpdate

/**
 * 实验室及器械检查
 */
public class LaboratoryCheckup implements Serializable {

    private static final long serialVersionUID = -7206967798503381386L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**mri*/
    @Column(name = "mri")
    private String mrc;

    /**CT*/
    private String ct;

    /**超声*/
    private String ultrasound;

    /**病理*/
    private String pathology;

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
