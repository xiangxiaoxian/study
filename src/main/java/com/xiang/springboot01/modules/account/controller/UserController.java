package com.xiang.springboot01.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.account.service.UserSerivce;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserController.java
 * @Description TODO
 * @createTime 2020年08月20日 13:39:00
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserSerivce userSerivce;


    /*
     * 127.0.0.1/api/user  ---post
     * {"userName":"xiangxiaoxian","password":"123"}
     * 注册
     * */
    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user) {
        return userSerivce.insertUser(user);
    }


    /*
     * 127.0.0.1/api/login  ---post
     * {"userName":"xiangxiaoxian","password":"123"}
     * 登录
     * */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> login(@RequestBody User user) {
        return userSerivce.login(user);
    }

    /*
     * 127.0.0.1/api/users  ---post
     * {"currentPage":"1","pageSize":"5","keyword":"x"}
     * */
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> selectUsersBySearchVo(@RequestBody SearchVo searchVo) {
        return userSerivce.selectUsersBySearchVo(searchVo);
    }


    /*
     * 127.0.0.1/api/user  ---put
     * {"userName":"xxx","userImg":"/aaa.jpg","userId":4}
     * */
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return userSerivce.updateUser(user);
    }

    /*
    * 127.0.0.1/api/user/6  delete
    * */
    @DeleteMapping("/user/{userId}")
    public Result<Object> deleteUser(@PathVariable int userId) {
        return userSerivce.deleteUser(userId);
    }

    /*
     * 127.0.0.1/api/user/3  get
     * */
    @GetMapping("/user/{userId}")
    public User selectUserByUserId(@PathVariable int userId) {
        return userSerivce.selectUserByUserId(userId);
    }
}