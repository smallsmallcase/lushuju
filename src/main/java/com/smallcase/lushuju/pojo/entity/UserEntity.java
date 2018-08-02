package com.smallcase.lushuju.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smallcase.lushuju.conventer.DateConverter;
import javafx.beans.DefaultProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Package: com.smallcase.lushuju.pojo.entity
 * Author: smallcase
 * Date: Created in 2018/7/2 13:49
 */
@Data
@Entity
@DynamicUpdate
public class UserEntity implements Serializable{

    private static final long serialVersionUID = 5438203234016412830L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //用户ID

    @NotNull
//    @Column(unique = true)
    private String username;//账号

//    private String name;//名称（昵称或者真实姓名，不同系统不同定义）

    private String password;//密码

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

//    private String salt;//加密密码的盐
//
//    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定


//    @ManyToMany(fetch=FetchType.EAGER)//立即从数据库中进行加载数据;
//    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") },
//            inverseJoinColumns ={@JoinColumn(name = "roleId") })
//    private List<SysRole> roleList;// 一个用户具有多个角色
//
//
//    /**
//     * 密码盐.
//     * @return
//     */
//    public String getCredentialsSalt(){
//        return this.username+this.salt;
//    }

//    @Override
//    public String toString() {
//        return "UserEntity{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", salt='" + salt + '\'' +
//                ", state=" + state +
//                ", roleList=" + roleList +
//                '}';
//    }
}
