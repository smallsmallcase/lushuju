package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * package: com.smallcase.lushuju.pojo.entity
 * date: 2018/11/27 18:43
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Entity(name = "class_to_person")
@Data
public class ClassToPerson {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//主键

    private Integer classId;//大类的ID

    private String personId;//personInfo的UUID

}
