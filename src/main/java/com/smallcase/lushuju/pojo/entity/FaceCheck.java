package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.DateConverter;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * package: com.smallcase.lushuju.pojo.entity
 * date: 2018/10/31 20:18
 *面部检查
 * @author smallcase
 * @since JDK 1.8
 */


@Data
@Entity(name = "face_check")
public class FaceCheck implements Serializable {
    private static final long serialVersionUID = 2162513959435183762L;
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
