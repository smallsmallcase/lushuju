package com.smallcase.lushuju.pojo.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/7/1 18:40
 */

@Deprecated
@Data
@Entity(name = "detail")

/**
 * 影像学检查数据中的三个细节
 */
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**分组*/
    private String groupName;

    /**拍摄、采集时间*/
    private Long sampleTime;

    /**测量分析值*/
    private String value;

    /**外键*/
    @OneToOne(targetEntity = PersonInfo.class)
    @JoinColumn(name = "fkey",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)

    private String personId;

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", sampleTime=" + sampleTime +
                ", value='" + value + '\'' +
                '}';
    }
}
