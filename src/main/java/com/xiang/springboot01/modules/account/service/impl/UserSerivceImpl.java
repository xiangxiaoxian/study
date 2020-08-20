package com.xiang.springboot01.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.dao.UserDao;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.account.service.UserSerivce;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import com.xiang.springboot01.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserSerivceImpl.java
 * @Description TODO
 * @createTime 2020年08月20日 13:35:00
 */
@Service
public class UserSerivceImpl implements UserSerivce {
   @Autowired
   private UserDao userDao;


    @Override
    public Result<User> insertUser(User user) {
        if (userDao.selectUserByUserName(user.getUserName())!=null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"user name is repeat");
        }
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"insert success",user);
    }

    @Override
    public  Result<User> login(User user) {
        User userTemp=userDao.selectUserByUserName(user.getUserName());
        if (userTemp!=null && userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
            return  new Result<User>(Result.ResultStatus.SUCCESS.status,"login success",userTemp);
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,"Username or password error");
    }

    @Override
    public PageInfo<User> selectUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<User>(Optional.ofNullable(userDao.selectUsersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }
}
