package com.smallcase.lushuju.pojo.view;

import lombok.Data;

/**
 * Package: com.smallcase.lushuju.pojo.view
 * Author: smallcase
 * Date: Created in 2018/7/5 21:46
 */

@Data
public class ResultVO<T> {

    //提示信息
    private String msg;

    //状态m码
    private Integer code;

    //具体内容
    private T content;
}
