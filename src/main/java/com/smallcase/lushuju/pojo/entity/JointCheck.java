package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.DateConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * package: com.smallcase.lushuju.pojo.entity
 * date: 2018/10/31 20:20
 *关节检查
 * @author smallcase
 * @since JDK 1.8
 */


@Data
@Entity(name = "joint_check")
public class JointCheck implements Serializable {

    private static final long serialVersionUID = 7020513576533263501L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;


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
