package com.smallcase.lushuju.utils.Exception;

import lombok.Data;

/**
 *
 * 自定义错误封装类
 * Package: com.smallcase.lushuju.utils
 * Author: smallcase
 * Date: Created in 2018/7/23 19:18
 */

@Data
public class MyException extends Exception {

    private static final long serialVersionUID = -1694779694605371823L;
    private Integer code;

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String msg, Integer code) {
        super(msg);
        this.code=code;

    }


}
