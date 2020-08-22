package com.xiang.springboot01.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RoleService.java
 * @Description TODO
 * @createTime 2020年08月21日 19:00:00
 */
public interface RoleService {
    List<Role> selectRole();

    Result<Role> insertRole(Role role);

    PageInfo<Role> selectRolesBySearchVo(SearchVo searchVo);

    Result<Role> updateRole(Role role);

    Result<Object> deleteRole(int roleId);

    Role selectRoleByRoleId(int roleId);
}
