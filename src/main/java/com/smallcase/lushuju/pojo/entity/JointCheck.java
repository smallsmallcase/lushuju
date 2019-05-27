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
 * 后面col开头的是预留字段，和关节检查无关
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



    /*
    接下来是预留字段
     */

    @Column(name = "col_a")
    private String colA;
    @Column(name = "col_b")
    private String colB;
    @Column(name = "col_c")
    private String colC;
    @Column(name = "col_d")
    private String colD;
    @Column(name = "col_e")
    private String colE;
    @Column(name = "col_f")
    private String colF;
    @Column(name = "col_g")
    private String colG;
    @Column(name = "col_h")
    private String colH;
    @Column(name = "col_i")
    private String colI;
    @Column(name = "col_j")
    private String colJ;
    @Column(name = "col_k")
    private String colK;
    @Column(name = "col_l")
    private String colL;
    @Column(name = "col_m")
    private String colM;
    @Column(name = "col_n")
    private String colN;
    @Column(name = "col_o")
    private String colO;
    @Column(name = "col_p")
    private String colP;
    @Column(name = "col_q")
    private String colQ;
    @Column(name = "col_r")
    private String colR;
    @Column(name = "col_s")
    private String colS;
    @Column(name = "col_t")
    private String colT;
    @Column(name = "col_u")
    private String colU;
    @Column(name = "col_v")
    private String colV;
    @Column(name = "col_w")
    private String colW;
    @Column(name = "col_x")
    private String colX;
    @Column(name = "col_y")
    private String colY;
    @Column(name = "col_z")
    private String colZ;

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
