package com.xiang.springboot01.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.account.service.RoleService;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RoleController.java
 * @Description TODO
 * @createTime 2020年08月21日 19:02:00
 */
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /*
    * 127.0.0.1/api/roles     ---get
    * */
    @GetMapping("/roles")
    public List<Role> selectRole() {
        return roleService.selectRole();
    }

    /*
     * 127.0.0.1/api/roles  ---post
     * {"currentPage":"1","pageSize":"5","keyword":"x"}
     * */
    @PostMapping(value = "/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Role> selectRolesBySearchVo(@RequestBody SearchVo searchVo) {
        return roleService.selectRolesBySearchVo(searchVo);
    }


    /*
     * 127.0.0.1/api/role/3  delete
     * */
    @DeleteMapping("/role/{roleId}")
    public Result<Object> deleteRole(@PathVariable int roleId) {
        return roleService.deleteRole(roleId);
    }


    /*
     * 127.0.0.1/api/role  ---post
     * {"roleName":"xiangxiaoxian"}
     *
     * */
    @PostMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> insertRole(@RequestBody Role role) {
        return roleService.insertRole(role);
    }


    /*
     * 127.0.0.1/api/role/3  get
     * */
    @GetMapping("/role/{roleId}")
    public Role selectRoleByRoleId(@PathVariable int roleId) {
        return roleService.selectRoleByRoleId(roleId);
    }

    /*
     * 127.0.0.1/api/role  ---put
     * {"roleName":"xxx","roleId":5}
     * */
    @PutMapping(value = "/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }
}
