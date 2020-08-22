package com.xiang.springboot01.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.dao.UserDao;
import com.xiang.springboot01.modules.account.dao.UserRoleDao;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.account.service.UserSerivce;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import com.xiang.springboot01.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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

   @Autowired
   private UserRoleDao userRoleDao;


    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        if (userDao.selectUserByUserName(user.getUserName())!=null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"user name is repeat");
        }
        user.setCreateDate(LocalDateTime.now());
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userDao.insertUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            /*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*/
            //JDK8版本
            roles.stream().forEach(item ->{
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }
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

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp=userDao.selectUserByUserName(user.getUserName());
        if (userTemp!=null && userTemp.getUserId()!=user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status,"user name is repeat");
        }
        userDao.updateUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            /*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*/
            //JDK8版本
            roles.stream().forEach(item ->{
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }
        return new Result<>(Result.ResultStatus.SUCCESS.status,"update success");
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delete success");
    }

    @Override
    public User selectUserByUserId(int userId) {
        return userDao.selectUserByUserId(userId);
    }
}
