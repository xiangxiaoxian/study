package com.xiang.springcloud.springCloudClientAccount.modules.account.service;

import com.xiang.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.xiang.springcloud.springCloudClientAccount.modules.account.service.impl.TestFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName FeignClient.java
 * @Description TODO
 * @createTime 2020年08月29日 15:27:00
 */
@FeignClient(value = "CLIENT-TEST",fallback = TestFeignClientFallback.class)
@Primary
public interface TestFeignClient {
    @GetMapping("/city/cities/{countryId}")
    List<City> selectCitiesByCountryId(@PathVariable int countryId);
 }
