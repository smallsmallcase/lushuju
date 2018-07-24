//package com.smallcase.lushuju.shiro;
//
//import com.smallcase.lushuju.pojo.entity.UserEntity;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//import java.util.HashMap;
//import java.util.HashSet;
//
///**
// * Package: com.smallcase.lushuju.shiro
// * Author: smallcase
// * Date: Created in 2018/7/4 10:05
// */
//public class MyShiroReaml extends AuthorizingRealm{
//
//    /**
//     * 授权用户权限
//     * @param principalCollection
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //获取用户
//        UserEntity user = (UserEntity) SecurityUtils.getSubject().getPrincipal();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        //设置用户角色
//        HashSet<String> roleSet = new HashSet<>();
//        roleSet.add("10002");
//        info.setRoles(roleSet);
//
//        //设置用户权限
//        HashSet<String> permissionSet = new HashSet<>();
//        permissionSet.add("添加权限");
//        permissionSet.add("权限删除");
//        info.addStringPermissions(permissionSet);
//
//        return info;
//    }
//
//
//    /**
//     * 验证用户身份
//     * @param authenticationToken
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String username = token.getUsername();
//        String password = String.valueOf(token.getPassword());
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("nickname", username);
//
//        //密码进行加密处理，明文为 password+name
//        String paw = password+username;
//        map.put("pswd", paw);
//
//        UserEntity user = new UserEntity();
//        user.setUsername(username);
//        user.setPassword(paw);
//        return null;
//    }
//}
