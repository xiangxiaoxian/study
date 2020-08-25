package com.xiang.springboot01.modules.account.dao;

import com.xiang.springboot01.modules.account.entity.Resource;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResourceDao.java
 * @Description TODO
 * @createTime 2020年08月22日 16:34:00
 */
@Mapper
@Repository
public interface ResourceDao  {
    @Select("select * from resource r left join role_resource rr on r.resource_id = rr.resource_id where rr.role_id = #{roleId}")
    List<Resource> selectResourcesByRoleId(int roleId);

    @Select("select * from resource")
    List<Resource> selectResource();

    @Select("<script>" +
            "select * from resource "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (resource_name like '%${keyWord}%' ) "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by resource_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<Resource> selectResourcesBySearchVo(SearchVo searchVo);

    @Insert("insert into resource (resource_uri,resource_name,permission) values (#{resourceUri},#{resourceName},#{permission})")
    void insertResource(Resource resource);

    @Update("update resource set resource_name = #{resourceName},resource_uri = #{resourceUri},permission = #{permission} where resource_id= #{resourceId}")
    void updateResource(Resource resource);

    @Delete("delete from resource where resource_id = #{resourceId}")
    void deleteResource(int resourceId);

    @Select("select * from resource where Resource_name=#{resourceName}")
    Resource selectResourceByResourceName(String ResourceName);

    @Select("select * from resource where resource_id=#{resourceId}")
    Resource selectResourceByResourceId(int resourceId);

    @Select("select * from resource resource left join role_resource roleResource on "
            + "resource.resource_id = roleResource.resource_id where roleResource.role_id = #{roleId}")
    List<Resource> getResourcesByRoleId(int roleId);
}
