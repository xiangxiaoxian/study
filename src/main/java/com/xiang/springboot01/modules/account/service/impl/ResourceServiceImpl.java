package com.xiang.springboot01.modules.account.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.dao.ResourceDao;
import com.xiang.springboot01.modules.account.dao.RoleResourceDao;
import com.xiang.springboot01.modules.account.entity.Resource;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.service.ResourceService;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResourceServiceImpl.java
 * @Description TODO
 * @createTime 2020年08月22日 17:04:00
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceDao roleResourceDao;

    @Override
    public List<Resource> selectResource() {
        return Optional.ofNullable(resourceDao.selectResource()).orElse(Collections.emptyList());
    }

    @Override
    @Transactional
    public Result<Resource> insertResource(Resource resource) {
        if (resourceDao.selectResourceByResourceName(resource.getResourceName())!=null){
            return new Result<Resource>(Result.ResultStatus.FAILD.status,"resource name is repeat");
        }
        resourceDao.insertResource(resource);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"insert success");
    }

    @Override
    public PageInfo<Resource> selectResourcesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<Resource>(Optional.ofNullable(resourceDao.selectResourcesBySearchVo(searchVo))
                                        .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<Resource> updateResource(Resource resource) {
        Resource resourceTemp=resourceDao.selectResourceByResourceName(resource.getResourceName());
        if (resourceTemp!=null && resourceTemp.getResourceId()!=resource.getResourceId()){
            return new Result<Resource>(Result.ResultStatus.FAILD.status,"resource name is repeat");
        }
        resourceDao.updateResource(resource);
        return new Result<Resource>(Result.ResultStatus.SUCCESS.status,"update success");
    }

    @Override
    @Transactional
    public Result<Object> deleteResource(int resourceId) {
        resourceDao.deleteResource(resourceId);
        roleResourceDao.deleteRoleResourceByResourceId(resourceId);
        return new Result<>(Result.ResultStatus.SUCCESS.status,"delete success");
    }

    @Override
    public Resource selectResourceByResourceId(int resourceId) {
        return resourceDao.selectResourceByResourceId(resourceId);
    }

    @Override
    public List<Resource> getResourcesByRoleId(int roleId) {
        return resourceDao.getResourcesByRoleId(roleId);
    }
}
