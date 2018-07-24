package com.smallcase.lushuju.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeanUtil {

    public static String[] getNullPropertyNames(Object source) {
        Set<String> emptyNames = getEmptyNames(source);
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static String[] getNullPropertyNames(Object source, String... ignoreProperties) {
        Set<String> emptyNames = getEmptyNames(source);
        for (String ignorePropertie : ignoreProperties) {
            emptyNames.add(ignorePropertie);
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private static Set<String> getEmptyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (StringUtils.isEmpty(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames;
    }

    public static String getUrlParam(Object source) {
        StringBuilder urlParmBuilder = new StringBuilder();
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if ("class".equals(pd.getName()) || StringUtils.isEmpty(srcValue)) {
                continue;
            }
            if (srcValue instanceof List) {
                List list = (List) srcValue;
                for (Object value : list) {
                    //注意此处只能处理字符串数组或者
                    urlParmBuilder.append(pd.getName()).append("=").append(value).append("&");
                }
            } else {
                String strValue = String.valueOf(srcValue);
                if (strValue.contains(",")) {
                    String[] strArray = strValue.split(",");
                    for (String str : strArray) {
                        urlParmBuilder.append(pd.getName()).append("=").append(str).append("&");
                    }
                } else {
                    urlParmBuilder.append(pd.getName()).append("=").append(srcValue).append("&");
                }
            }
        }
        String urlParam = urlParmBuilder.toString().replace("\"","");
        return urlParam;
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static void copyPropertiesIgnoreNull(Object source, Object target, String... ignoreProperties) throws BeansException {
        String[] newIgnoreProperties = getNullPropertyNames(source, ignoreProperties);

        BeanUtils.copyProperties(source, target, newIgnoreProperties);
    }
}
