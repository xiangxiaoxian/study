package com.xiang.springboot01.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.xiang.springboot01.modules.account.dao.RoleDao;
import com.xiang.springboot01.modules.account.dao.RoleResourceDao;
import com.xiang.springboot01.modules.account.dao.UserDao;
import com.xiang.springboot01.modules.account.dao.UserRoleDao;
import com.xiang.springboot01.modules.account.entity.*;

import com.xiang.springboot01.modules.account.service.RoleService;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RoleServiceImpl.java
 * @Description TODO
 * @createTime 2020年08月21日 19:00:00
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Role> selectRole() {
        return Optional.ofNullable(roleDao.selectRole()).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Result<Role> insertRole(Role role) {
        if (roleDao.selectRoleByRoleName(role.getRoleName())!=null){
            return new Result<Role>(Result.ResultStatus.FAILD.status,"role name is repeat");

        }
        roleDao.insertRole(role);
        roleResourceDao.deleteRoleResourceByRoleId(role.getRoleId());
        List<Resource> resources=role.getResources();
        if (resources!=null && !resources.isEmpty()){
            /*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*/
            //JDK8版本
            resources.stream().forEach(item ->{
                System.out.println(item.getResourceId());
                roleResourceDao.insertRoleResource(role.getRoleId(),item.getResourceId());
            });
        }
        return new Result<Role>(Result.ResultStatus.SUCCESS.status,"insert success");
    }

    @Override
    public PageInfo<Role> selectRolesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Role>(Optional.ofNullable(roleDao.selectRolesBySearchVo(searchVo)).orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<Role> updateRole(Role role) {
        Role roleTemp=roleDao.selectRoleByRoleName(role.getRoleName());
        if (roleTemp!=null && roleTemp.getRoleId()!=role.getRoleId()){
            return new Result<Role>(Result.ResultStatus.FAILD.status,"role name is repeat");

        }
        roleDao.updateRole(role);
        roleResourceDao.deleteRoleResourceByRoleId(role.getRoleId());
        List<Resource> resources=role.getResources();
        if (resources!=null && !resources.isEmpty()){
            /*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*/
            //JDK8版本
            resources.stream().forEach(item ->{
                roleResourceDao.insertRoleResource(role.getRoleId(),item.getResourceId());
            });
        }
        return new Result<>(Result.ResultStatus.SUCCESS.status,"update success");
    }

    @Override
    @Transactional
    public Result<Object> deleteRole(int roleId) {
        roleDao.deleteRole(roleId);
        userRoleDao.deleteUserRoleByRoleId(roleId);
        roleResourceDao.deleteRoleResourceByRoleId(roleId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delete success");
    }

    @Override
    public Role selectRoleByRoleId(int roleId) {
        return roleDao.selectRoleByRoleId(roleId);
    }


}
