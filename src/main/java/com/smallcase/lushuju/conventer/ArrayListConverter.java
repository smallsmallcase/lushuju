package com.smallcase.lushuju.conventer;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Package: com.smallcase.lushuju.conventer
 * Author: smallcase
 * Date: Created in 2018/7/6 10:36
 */

@Slf4j
public class ArrayListConverter implements AttributeConverter<List, String>{

    @Override
    public String convertToDatabaseColumn(List list) {
        if (list == null) {
            log.info("source is null");
            return "";
        }
        try {
//            return String.join(",", list);
            return  (String) list.stream().collect(Collectors.joining(","));
        } catch (Exception e) {
            log.error("object conventer error");
            return "";
        }
    }

    @Override
    public List convertToEntityAttribute(String s) {
        String[] array = s.split(",");
        return Arrays.asList(array);
    }
}
