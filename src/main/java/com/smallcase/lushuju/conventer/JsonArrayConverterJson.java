package com.smallcase.lushuju.conventer;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * 将对象属性转换为JSON字符串，保存数据库；或者从数据库读取json转为对象
 * Package: com.smallcase.lushuju.conventer
 * Author: smallcase
 * Date: Created in 2018/7/6 10:38
 */

@Slf4j
public class JsonArrayConverterJson implements AttributeConverter<JSONArray,String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(JSONArray objects) {
        if (objects == null) {
            log.info("no data found in this convert");
            return null;
        }
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            log.error("convert to string failed：" + e.getMessage());
            return "";
        }
    }

    @Override
    public JSONArray convertToEntityAttribute(String s) {
        if (StringUtils.isEmpty(s)) {
            log.info("转换中，字符串是空的");
            return null;
        }

        try {
            return objectMapper.readValue(s,JSONArray.class);
        } catch (IOException e) {
            log.error("convert to json failed" + e.getMessage());
            return null;
        }
    }
}
