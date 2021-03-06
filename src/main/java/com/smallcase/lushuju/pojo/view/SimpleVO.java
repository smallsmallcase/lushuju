package com.smallcase.lushuju.pojo.view;

import lombok.Data;

/**
 * package: com.smallcase.lushuju.pojo.view
 * date: 2018/10/25 19:32
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Data
public class SimpleVO<T> {
    //提示信息
    private String msg;

    //状态m码
    private Integer code;

    //具体内容
    private T userId;

    //录入的病人个数
    private int recordedNumber;

    //管理员为0，普通用户是1
    private int roleId;
}
