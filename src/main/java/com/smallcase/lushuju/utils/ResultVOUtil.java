package com.smallcase.lushuju.utils;

import com.smallcase.lushuju.pojo.view.ResultVO;

/**
 * Package: com.smallcase.lushuju.utils
 * Author: smallcase
 * Date: Created in 2018/7/14 17:19
 */

@Deprecated
public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setContent(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;

    }

    public static ResultVO success() {
        return success(null);
    }


    public static ResultVO error(String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setMsg(msg);
        resultVO.setCode(1);
        return resultVO;
    }

}
