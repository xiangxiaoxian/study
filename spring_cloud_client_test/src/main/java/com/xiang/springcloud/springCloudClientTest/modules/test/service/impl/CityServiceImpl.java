package com.xiang.springcloud.springCloudClientTest.modules.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiang.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.xiang.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.xiang.springcloud.springCloudClientTest.modules.test.dao.CityDao;
import com.xiang.springcloud.springCloudClientTest.modules.test.entity.City;
import com.xiang.springcloud.springCloudClientTest.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CityServiceImpl.java
 * @Description TODO
 * @createTime 2020年08月11日 16:37:00
 */

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> selectCityByCountryId(int countryId) {
        return Optional.ofNullable(cityDao.selectCityByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> selectCitiesBySearchVo(int countryId, SearchVo searchVo) {
       searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional.ofNullable(cityDao.selectCityByCountryId(countryId)).
                orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> selectCitiesBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<>(Optional.ofNullable(cityDao.selectCitiesBySearchVo(searchVo)).
                orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        city.setDateCreated(new Date());
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status, "Insert success.", city);
    }

    @Override
//    @Transactional
    @Transactional(noRollbackFor = ArithmeticException.class)
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        int i=11111/0;
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"update success",city);
    }

    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,"删除成功");
    }
}
