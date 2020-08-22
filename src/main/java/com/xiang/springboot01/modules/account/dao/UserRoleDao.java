package com.xiang.springboot01.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserRoleDao.java
 * @Description TODO
 * @createTime 2020年08月21日 16:21:00
 */
@Repository
@Mapper
public interface UserRoleDao {

    @Delete("delete from user_role where user_id = #{userId}")
    void deleteUserRoleByUserId(int userId);

    @Delete("delete from user_role where role_id = #{roleId}")
    void deleteUserRoleByRoleId(int roleId);

    @Insert("insert into user_role (user_id,role_id) values(#{userId},#{roleId})")
    void insertUserRole(int userId,int roleId);
}
