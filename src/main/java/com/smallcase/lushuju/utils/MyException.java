package com.smallcase.lushuju.utils;

/**
 *
 * 自定义错误封装类
 * Package: com.smallcase.lushuju.utils
 * Author: smallcase
 * Date: Created in 2018/7/23 19:18
 */
public class MyException extends Exception {

    private String[] args;

    public MyException(String msg) {
        super(msg);
    }

    public MyException(String msg, String... args) {
        super(msg);
        this.args = args;

    }

    public String[] getArgs() {
        return this.args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

}
