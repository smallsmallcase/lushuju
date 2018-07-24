package com.smallcase.lushuju.shiro;

/**
 * Package: com.smallcase.lushuju.shiro
 * Author: smallcase
 * Date: Created in 2018/7/4 15:00
 */

import com.smallcase.lushuju.pojo.entity.SysPermission;
import com.smallcase.lushuju.pojo.entity.SysRole;
import com.smallcase.lushuju.pojo.entity.UserEntity;
import com.smallcase.lushuju.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

/**
 * 身份校验核心类
 */
public class MyShiroRealm2 extends AuthenticatingRealm{

    @Resource
    private UserInfoService userInfoService;

//    /**
//     * 授权
//     * @param principalCollection
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//
//        return null; //简单的身份认证，不需要权限控制
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
//
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserEntity userEntity = (UserEntity) principalCollection.getPrimaryPrincipal();
//
//        for (SysRole role : userEntity.getRoleList()) {
//            authorizationInfo.addRole(role.getRole());
//            for (SysPermission permission : role.getPermissions()) {
//                authorizationInfo.addStringPermission(permission.getPermission());
//            }
//        }
//        return authorizationInfo;
//    }


    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取用户的输入账号

        String username = token.getUsername();
        //获取用户的密码
        char[] chars = token.getPassword();
        String password = String.valueOf(chars);
//        System.out.println(token.getCredentials());

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserEntity userEntity = userInfoService.findByUsernameAndPassword(username, password);
        System.out.println("----->>userInfo=" + userEntity);

        if (userEntity == null) {
            return null;
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(

                userEntity.getUsername(),//用户名
                userEntity.getPassword(),
//                ByteSource.Util.bytes(userEntity.getCredentialsSalt()), //salt=username+salt
                super.getName()
        );

        return authenticationInfo;

    }

//    @Override
//    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
//
//    }

}
