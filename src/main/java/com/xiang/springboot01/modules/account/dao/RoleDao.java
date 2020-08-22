package com.xiang.springboot01.modules.account.dao;

import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.entity.User;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RoleDao.java
 * @Description TODO
 * @createTime 2020年08月21日 16:28:00
 */
@Repository
@Mapper
public interface RoleDao {

    @Select("select * from role r left join user_role ur on r.role_id = ur.role_id where ur.user_id = #{userId}")
    List<Role> selectRolesByUserId(int userId);

    @Select("select * from role")
    List<Role> selectRole();

    @Select("<script>" +
            "select * from role "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (role_name like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by role_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Role> selectRolesBySearchVo(SearchVo searchVo);

    @Insert("insert into role (role_name) values (#{roleName})")
    @Options(useGeneratedKeys = true,keyColumn = "role_id",keyProperty = "roleId")
    void insertRole(Role role);

    @Update("update role set role_name = #{roleName}  where role_id= #{roleId}")
    void updateRole(Role role);

    @Delete("delete from role where role_id = #{roleId}")
    void deleteRole(int roleId);

    @Select("select * from role where role_name=#{roleName}")
    Role selectRoleByRoleName(String roleName);

    @Select("select * from role where role_id=#{roleId}")
    Role selectRoleByRoleId(int roleId);


}
