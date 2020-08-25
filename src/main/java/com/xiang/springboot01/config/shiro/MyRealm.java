package com.xiang.springboot01.config.shiro;

import com.xiang.springboot01.modules.account.entity.Resource;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.account.service.ResourceService;
import com.xiang.springboot01.modules.account.service.UserSerivce;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Realm.java
 * @Description TODO
 * @createTime 2020年08月25日 18:39:00
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserSerivce userSerivce;

    @Autowired
    private ResourceService resourceService;
    //验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        User user =(User) principals.getPrimaryPrincipal();
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            for (Role role : roles) {
                simpleAuthorizationInfo.addRole(role.getRoleName());
                List<Resource> resources = resourceService.getResourcesByRoleId(role.getRoleId());
                if (resources!=null && !resources.isEmpty()){
                for (Resource resource : resources) {
                    simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    }
                }
            }
        }

        return simpleAuthorizationInfo;
    }


    //授权
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
       String userName=(String) token.getPrincipal();
        User user=userSerivce.selectUserByUserName(userName);
        if (user ==null){
            throw new UnknownAccountException("当前用户不存在");
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
