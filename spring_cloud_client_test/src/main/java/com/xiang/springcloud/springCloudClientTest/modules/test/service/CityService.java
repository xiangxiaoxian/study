package com.xiang.springcloud.springCloudClientTest.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.xiang.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.xiang.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.xiang.springcloud.springCloudClientTest.modules.test.entity.City;


import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CityService.java
 * @Description TODO
 * @createTime 2020年08月11日 16:37:00
 */
public interface CityService {

    List<City> selectCityByCountryId(int countryId);

    PageInfo<City> selectCitiesBySearchVo(int countryId, SearchVo searchVo);

    PageInfo<City> selectCitiesBySearchVo(SearchVo searchVo);

    Result<City> insertCity(City city);

    Result<City> updateCity(City city);

    Result<Object> deleteCity(int cityId);

}
