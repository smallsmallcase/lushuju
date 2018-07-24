package com.smallcase.lushuju.pojo.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/7/1 18:21
 */

@Deprecated
@Data
@Entity(name = "fig_check")

/**
 * 影像学检查数据
 */

public class FigCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**分组*/
    private String groupName;

    /**面像.jpg*/
    private String faceImg;

    /**手腕片*/
    private String handImg;

    /**各种影像测量分析值*/
    private String imgData;

    /**外键*/
    @OneToOne(targetEntity = PersonInfo.class)
    @JoinColumn(name = "fkey",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String personId;

    @Override
    public String toString() {
        return "FigCheck{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", faceImg='" + faceImg + '\'' +
                ", handImg='" + handImg + '\'' +
                ", imgData='" + imgData + '\'' +
                '}';
    }
}
