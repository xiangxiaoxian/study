package com.xiang.springcloud.springCloudClientTest.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.xiang.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.xiang.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.xiang.springcloud.springCloudClientTest.modules.test.entity.City;
import com.xiang.springcloud.springCloudClientTest.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CityController.java
 * @Description TODO
 * @createTime 2020年08月11日 16:41:00
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     *127.0.0.1/city/cities/522  ---get
     * 查询 id
     */
    @GetMapping("/cities/{countryId}")
    public List<City> selectCityByCountryId(@PathVariable int countryId){
        return cityService.selectCityByCountryId(countryId);
    }


    /**
     *127.0.0.1/city/cities/522  ---post
     * {"currentPage":"1","pageSize":"5"}
     * 脚本查询
     */
    @PostMapping(value = "/cities/{countryId}",consumes = "application/json")
    public PageInfo<City> selectCitiesBySearchVo(@PathVariable int countryId,@RequestBody SearchVo searchVo){
        return cityService.selectCitiesBySearchVo(countryId,searchVo);
    }

    /**
     *127.0.0.1/city/cities  ---post
     * {"currentPage":"1","pageSize":"5","keyWord":"sh","orderBy":"city_name","sort":"desc"}
     * 脚本查询排序
     */
    @PostMapping(value = "/cities" ,consumes = "application/json")
    public PageInfo<City> selectCitiesBySearchVo(SearchVo searchVo){
        return cityService.selectCitiesBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/city/city ---- post
     * {"cityName":"test1","localCityName":"freeCity","countryId":"522"}
     * 添加，json
     */
    @PostMapping(value = "/city",consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city) {
        return cityService.insertCity(city);
    }

    /**
     * 127.0.0.1/city/city ---- put
     * "cityId"="2258","cityName"="aaaa"
     * 修改，form表单
     */
    @PutMapping(value = "/city",consumes = "application/x-www-form-urlencoded")//form表单提交
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /**
     * 127.0.0.1/city/city/2258 ---- delete
     *删除
     */
    @DeleteMapping("/city/{cityId}")
    public Result<Object> deleteCity(@PathVariable int cityId){
        return cityService.deleteCity(cityId);
    }


}
