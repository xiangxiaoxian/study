package com.xiang.springboot01.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserSerivce.java
 * @Description TODO
 * @createTime 2020年08月20日 13:35:00
 */
public interface UserSerivce {

    Result<User> insertUser(User user);

    Result<User> login(User user);

    PageInfo<User> selectUsersBySearchVo(SearchVo searchVo);

    Result<User> updateUser(User user);

    Result<Object> deleteUser(int userId);

    User selectUserByUserId(int userId);
}
