package com.smallcase.lushuju.conventer;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import java.util.Date;

/**
 * Package: com.smallcase.lushuju.conventer
 * Author: smallcase
 * Date: Created in 2018/7/6 11:57
 */
@Slf4j
public class DateConverter implements AttributeConverter<Long, Date>{

    private static final Long MIN_TIME = 1000000000000L;
    @Override
    public Date convertToDatabaseColumn(Long meta) {
        if (meta == null) {
            return new Date();
        }

        if (meta.compareTo(MIN_TIME) < 0) {
            meta = meta * 1000;
        }

        return new Date(meta);
    }

    @Override
    public Long convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        }
        return date.getTime();
    }
}
