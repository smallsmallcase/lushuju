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
 * Date: Created in 2018/6/30 11:04
 */

@Data
@Entity(name = "clinical_examination")
@DynamicUpdate


/**
 * 面部检查和关节检查
 */
public class ClinicalExamination implements Serializable{

    private static final long serialVersionUID = -236106222008316636L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    /**正面面型*/
    private String faceShapeFront;

    /**面部对称性*/
    private String faceSymmetry;

    /**面中间1/3凸度*/
    private String faceConvexity;

    /**口唇*/
    private String lips;

    /**侧面面型*/
    private String faceShapeSide;

    /**鼻唇角*/
    private String nasolabialAngle;

    /**颌骨*/
    private String jaw;

    /**软组织*/
    private String softTissue;

    /**开口型*/
    private  String openType;

    /**开口度*/
    private String openAngle;

    /**弹响*/
    private String snap;

    /**疼痛*/
    private String ache;

//    /**外键*/
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
