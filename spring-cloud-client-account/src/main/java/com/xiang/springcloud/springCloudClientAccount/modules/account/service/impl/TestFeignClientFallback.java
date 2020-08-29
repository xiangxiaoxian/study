package com.xiang.springcloud.springCloudClientAccount.modules.account.service.impl;

import com.xiang.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.xiang.springcloud.springCloudClientAccount.modules.account.service.TestFeignClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName TestFeignClientFallback.java
 * @Description TODO
 * @createTime 2020年08月29日 16:22:00
 */
@Component
public class TestFeignClientFallback implements TestFeignClient {
    @Override
    public List<City> selectCitiesByCountryId(int countryId) {
        return new ArrayList<>();
    }
}
