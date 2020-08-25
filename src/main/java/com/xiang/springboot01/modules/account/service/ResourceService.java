package com.xiang.springboot01.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.entity.Resource;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResourceService.java
 * @Description TODO
 * @createTime 2020年08月22日 17:04:00
 */
public interface ResourceService {
    List<Resource> selectResource();

    Result<Resource> insertResource(Resource resource);

    PageInfo<Resource> selectResourcesBySearchVo(SearchVo searchVo);

    Result<Resource> updateResource(Resource resource);

    Result<Object> deleteResource(int resourceId);

    Resource selectResourceByResourceId(int resourceId);

    List<Resource> getResourcesByRoleId(int roleId);
}
