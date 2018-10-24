package com.smallcase.lushuju.utils.Bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * package: com.smallcase.lushuju.utils.Bean
 * date: 2018/10/24 10:12
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Configuration
public class BeanFactory {


    /**
     * 该bean必须依赖Apache的
     * @return
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //设置上传文件的大小20M
        resolver.setMaxUploadSize(20971520);
        //设置请求的编码格式
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

}
