package com.smallcase.lushuju.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * package: com.smallcase.lushuju.configure
 * date: 2018/10/26 15:20
 *
 * @author smallcase
 * @since JDK 1.8
 */

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }


    /**
     * 拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {


        /*
        登陆拦截
         */
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**")
                .excludePathPatterns("/register/**")
                .excludePathPatterns("/mmmm")
                .excludePathPatterns("/upload/img")
                .excludePathPatterns("/upload/excel")
                .excludePathPatterns("/download/img")
                .excludePathPatterns("/thy/**")
                .excludePathPatterns("/aa")
                .excludePathPatterns("/index/**");

//                .addPathPatterns("/waike/**")
//                .addPathPatterns("/search/**")
//                .addPathPatterns("/changestatus")
//                .excludePathPatterns()
//                .addPathPatterns("/zhenji/**");

        /*
        低等用户拦截
         */
        registry.addInterceptor(new UserOauthInterceptor())
                .addPathPatterns("/waike/**")
                .addPathPatterns("/search/**")
                .addPathPatterns("/external/**")
//                .addPathPatterns("/changestatus")
                .addPathPatterns("/zhenji/**");
        /*
        高等和普通用户拦截
         */
        registry.addInterceptor(new EditOauthIntercepter())
                .addPathPatterns("/waike/edit/**")
                .addPathPatterns("/zhenji/edit/**")
                .addPathPatterns("/search/userInfo")
                .addPathPatterns("/changestatus")
                .addPathPatterns("/bigclass/**")
                .addPathPatterns("/delete")
                .excludePathPatterns("/bigclass/searchAll");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }


    /**
     * 跨域访问配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许所有的跨域
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders()
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(3600)
//                .allowCredentials(true);

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/index");

    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
