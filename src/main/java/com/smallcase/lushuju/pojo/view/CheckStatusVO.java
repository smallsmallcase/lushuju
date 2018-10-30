package com.smallcase.lushuju.pojo.view;

import lombok.Data;

/**
 * package: com.smallcase.lushuju.pojo.view
 * date: 2018/10/30 17:01
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Data
public class CheckStatusVO {
    //
    private String userName;

    //具体内容
    private Integer userId;

    //录入的病人个数
    private int recordedNumber;
}
