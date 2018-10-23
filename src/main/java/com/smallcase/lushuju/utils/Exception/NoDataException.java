package com.smallcase.lushuju.utils.Exception;

import lombok.Data;

/**
 * Package: com.smallcase.lushuju.utils.Exception
 * Author: smallcase
 * Date: Created in 2018/10/23 9:42
 */

public class NoDataException extends Exception {


    private static final long serialVersionUID = 6846037455447551768L;


    public NoDataException(String msg) {
        super(msg);

    }
}
