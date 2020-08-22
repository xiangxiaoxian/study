package com.xiang.springboot01.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.xiang.springboot01.modules.account.entity.Resource;
import com.xiang.springboot01.modules.account.entity.Role;
import com.xiang.springboot01.modules.account.service.ResourceService;
import com.xiang.springboot01.modules.common.vo.Result;
import com.xiang.springboot01.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResourceController.java
 * @Description TODO
 * @createTime 2020年08月22日 18:02:00
 */
@RestController
@RequestMapping("/api")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /*
     * 127.0.0.1/api/resources     ---get
     * */
    @GetMapping("/resources")
    public List<Resource> selectResource() {
        return resourceService.selectResource();
    }

    /*
     * 127.0.0.1/api/resource  ---post
     * {"resourceUri":"www.llll","resourceName":"第三个","permission":"第三"}
     *
     * */
    @PostMapping(value = "/resource", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> insertResource(@RequestBody Resource resource) {
        return resourceService.insertResource(resource);
    }

    /*
     * 127.0.0.1/api/resources  ---post
     * {"currentPage":"1","pageSize":"5","keyWord":"w"}
     * */
    @PostMapping(value = "/resources", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<Resource> selectResourcesBySearchVo(@RequestBody SearchVo searchVo) {
        return resourceService.selectResourcesBySearchVo(searchVo);
    }


    /*
     * 127.0.0.1/api/resource  ---put
     * {"resourceName":"xxx","resourceId":3,"permission":"第三","resourceUri":"www.llll"}
     * */
    @PutMapping(value = "/resource", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<Resource> updateResource(@RequestBody Resource resource) {
        return resourceService.updateResource(resource);
    }


    /*
     * 127.0.0.1/api/resource/3  delete
     * */
    @DeleteMapping("/resource/{resourceId}")
    public Result<Object> deleteResource(@PathVariable int resourceId) {
        return resourceService.deleteResource(resourceId);
    }

    /*
     * 127.0.0.1/api/resource/3  get
     * */
    @GetMapping("/resource/{resourceId}")
    public Resource selectResourceByResourceId(@PathVariable int resourceId) {
        return resourceService.selectResourceByResourceId(resourceId);
    }
}
