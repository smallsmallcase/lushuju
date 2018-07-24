package com.smallcase.lushuju.pojo.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/7/1 18:56
 */

@Deprecated
@Data
@Entity(name = "analysis")

/**
 * 诊断
 */
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    private String personId;

    /**安氏分类*/
    private String anshi;

    /**毛氏分类*/
    private String maoshi;

    /**治疗要点*/
    private String treatPoint;

    /**外键*/
//    @OneToOne(targetEntity = PersonInfo.class)
//    @JoinColumn(name = "fkey",referencedColumnName = "id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "fkey")
    private String personId;

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", anshi='" + anshi + '\'' +
                ", maoshi='" + maoshi + '\'' +
                ", treatPoint='" + treatPoint + '\'' +
                '}';
    }
}
