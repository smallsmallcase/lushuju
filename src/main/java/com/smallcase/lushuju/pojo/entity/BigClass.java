package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * package: com.smallcase.lushuju.pojo.entity
 * date: 2018/11/27 16:28
 *
 * @author smallcase
 * @since JDK 1.8
 */
@Data
@Entity(name = "big_class")
public class BigClass implements Serializable {

    @Id
    @JsonIgnore
    private Integer id;//主键

    private String className;//大类的名字



}
