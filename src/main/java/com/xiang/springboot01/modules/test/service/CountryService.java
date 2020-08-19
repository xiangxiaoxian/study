package com.xiang.springboot01.modules.test.service;

import com.xiang.springboot01.modules.test.entity.Country;

/**
 * @author xiangxiaoxian
 * @version 1.0.0
 * @ClassName CountryService.java
 * @Description TODO
 * @createTime 2020年08月11日 16:25:00
 */
public interface CountryService {

    Country selectCountryByCountryId(int countryId);

    Country selectCountryByCountryName(String countryName);

    Country mograteCountryByRedis(int countryId);
}
