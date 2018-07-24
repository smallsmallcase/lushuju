package com.smallcase.lushuju.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * Package: com.smallcase.lushuju.shiro
 * Author: smallcase
 * Date: Created in 2018/7/3 18:48
 */

@Configuration
@Slf4j
public class ShiroConfiguration {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        log.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器
        LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
////        filterChainMap.put("/logout", "logout");
//        filterChainMap.put("/login", "anon");
        filterChainMap.put("/register", "anon");
        filterChainMap.put("/addregister", "anon");
//        filterChainMap.put("/login", "anon");
        filterChainMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);



        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登陆成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");


        return shiroFilterFactoryBean;
    }

//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        hashedCredentialsMatcher.setHashIterations(10);
//        return hashedCredentialsMatcher;
//    }

    @Bean
    public MyShiroRealm2 myShiroRealm2(){
        MyShiroRealm2 myShiroRealm2 = new MyShiroRealm2();
//        myShiroRealm2.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm2;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm2());
        return manager;
    }

}
