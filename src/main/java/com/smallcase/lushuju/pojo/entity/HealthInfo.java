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
 * Date: Created in 2018/6/18 19:40
 */
@Data
@Entity(name = "health_info")
@DynamicUpdate
public class HealthInfo implements Serializable{

    private static final long serialVersionUID = 3947842114616309209L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**体温*/
    private String temperature;

    /**脉搏*/
    private String pulse;

    /**呼吸*/
    private String breathe;

    /**血压*/
    private String bloodPressure;

    /**身高*/
    private String height;

    /**体重*/
    private String weight;

    /**一般情况*/
    private String generalCase;

    /**皮肤，粘膜*/
    private String skin;

    /**淋巴结*/
    private String lymphGland;

    /**头部及器官*/
    private String headOrgan;

    /**眼*/
    private String eyes;

    /**耳*/
    private String ear;

    /**鼻*/
    private String nose;

    /**口腔*/
    private String mouth;

    /**颈部*/
    private String neck;

    /**胸部*/
    private String chest;

    /**肺部*/
    private String lung;

    /**心脏*/
    private String heart;

    /**桡动脉*/
    private String artery;

    /**周围血管症*/
    private String around;

    /**腹部*/
    private String belly;

    /**肛门*/
    private String door;

    /**脊椎*/
    private String spine;

    /**四肢*/
    private String limbs;

    /**神经反射*/
    private String reflect;


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
