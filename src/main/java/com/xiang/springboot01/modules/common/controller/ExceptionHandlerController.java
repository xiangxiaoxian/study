package com.xiang.springboot01.modules.common.controller;

import com.xiang.springboot01.modules.common.vo.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ExceptionHandlerController.java
 * @Description TODO
 * @createTime 2020年08月26日 19:14:00
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerController {

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public Result<String> handler403(){
        return new Result<>(Result.ResultStatus.FAILD.status,"","/common/403");
    }
}
