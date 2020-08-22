package com.xiang.springboot01.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RoleResourceDao.java
 * @Description TODO
 * @createTime 2020年08月22日 16:35:00
 */
@Mapper
@Repository
public interface RoleResourceDao {

    @Delete("delete from role_resource where role_id = #{roleId}")
    void deleteRoleResourceByRoleId(int roleId);

    @Delete("delete from role_resource where resource_id = #{resourceId}")
    void deleteRoleResourceByResourceId(int resourceId);

    @Insert("insert into role_resource (resource_id,role_id) values(#{resourceId},#{roleId})")
    void insertRoleResource(int roleId,int resourceId);
}
