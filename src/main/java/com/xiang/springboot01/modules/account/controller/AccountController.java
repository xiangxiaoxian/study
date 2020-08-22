package com.xiang.springboot01.modules.account.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName AccountController.java
 * @Description TODO
 * @createTime 2020年08月17日 15:55:00
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    /*
    * 127.0.0.1/account/users  ---get
    * */
    @GetMapping("/users")
    public String usersPage(){
        return "index";
    }

    /*
     * 127.0.0.1/account/roles  ---get
     * */
    @GetMapping("/roles")
    public String rolesPage(){
        return "index";
    }

    /*
     * 127.0.0.1/account/resources  ---get
     * */
    @GetMapping("/resources")
    public String resourcesPage(){
        return "index";
    }

    /*
    * 127.0.0.1/account/login  ---get
    * */
    @GetMapping("/login")
    public String loginPage(){
        return "indexSimple";
    }

    /*
     * 127.0.0.1/account/register  ---get
     * */
    @GetMapping("/register")
    public String registerPage(){
        return "indexSimple";
    }
}
