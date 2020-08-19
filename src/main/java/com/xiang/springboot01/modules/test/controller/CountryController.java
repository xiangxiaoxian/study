package com.xiang.springboot01.modules.test.controller;

import com.xiang.springboot01.modules.test.entity.Country;
import com.xiang.springboot01.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiangxiaoxian
 * @version 1.0.0
 * @ClassName CountryController.java
 * @Description TODO
 * @createTime 2020年08月11日 16:30:00
 */
@RestController
@RequestMapping("/coun")
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     * 127.0.0.1/coun/country/522  ---get
     *
     */
    @GetMapping("/country/{countryId}")
    public Country selectCountryByCountryId(@PathVariable int countryId){
        return countryService.selectCountryByCountryId(countryId);
    }

    /**
     * 127.0.0.1/coun/country?countryName=china  ---get
     */
    @GetMapping("/country")
    public Country selectCountryByCountryName(@RequestParam String countryName){
        return countryService.selectCountryByCountryName(countryName);
    }

    /**
     * 127.0.0.1/coun/redis/country/522  ---get
     *
     */
    @GetMapping("/redis/country/{countryId}")
    public Country mograteCountryByRedis(@PathVariable int countryId) {
        return countryService.mograteCountryByRedis(countryId);
    }

}
