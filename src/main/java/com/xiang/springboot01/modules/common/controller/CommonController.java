package com.xiang.springboot01.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.ConstructorResult;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CommonController.java
 * @Description TODO
 * @createTime 2020年08月20日 16:58:00
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    /*
    * 127.0.0.1/common/dashboard  ---get
    * */
    @GetMapping("/dashboard")
    public String dashboardPage(){
        return "index";
    }

    /*
     * 127.0.0.1/common/403  ---get
     * */
    @GetMapping("/403")
    public String errorPageFor403(){
        return "index";
    }

    /*
     * 127.0.0.1/common/404  ---get
     * */
    @GetMapping("/404")
    public String errorPageFor404(){
        return "index";
    }

    /*
     * 127.0.0.1/common/500  ---get
     * */
    @GetMapping("/500")
    public String errorPageFor500(){
        return "index";
    }
}
