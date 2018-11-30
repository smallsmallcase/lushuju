package com.smallcase.lushuju.pojo.view;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

/**
 * package: com.smallcase.lushuju.pojo.view
 * date: 2018/11/29 21:21
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Data
public class PersonIdsArray {

    private JSONArray personIdArray;
    private Integer totalNum;

}
