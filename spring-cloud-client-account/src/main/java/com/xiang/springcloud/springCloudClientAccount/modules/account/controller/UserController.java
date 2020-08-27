package com.xiang.springcloud.springCloudClientAccount.modules.account.controller;



import com.github.pagehelper.PageInfo;
import com.xiang.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.xiang.springcloud.springCloudClientAccount.modules.account.service.UserSerivce;
import com.xiang.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.xiang.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 127.0.0.1/api/user/3  get
     * */
    @GetMapping("/user/{userId}")
    public User selectUserByUserId(@PathVariable int userId) {
        return userSerivce.selectUserByUserId(userId);
    }




}