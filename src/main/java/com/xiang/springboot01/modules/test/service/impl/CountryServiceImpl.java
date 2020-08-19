package com.xiang.springboot01.modules.test.service.impl;

import com.xiang.springboot01.modules.test.dao.CountryDao;
import com.xiang.springboot01.modules.test.entity.Country;
import com.xiang.springboot01.modules.test.service.CountryService;
import com.xiang.springboot01.utils.RedisUtils;
import io.netty.channel.ChannelOutboundBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xiangxiaoxian
 * @version 1.0.0
 * @ClassName CountryServiceImpl.java
 * @Description TODO
 * @createTime 2020年08月11日 16:25:00
 */


@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDao countryDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country selectCountryByCountryId(int countryId) {
        return countryDao.selectCountryByCountryId(countryId);
    }

    @Override
    public Country selectCountryByCountryName(String countryName) {
        return countryDao.selectCountryByCountryName(countryName);
    }

    @Override
    public Country mograteCountryByRedis(int countryId) {
        Country country=countryDao.selectCountryByCountryId(countryId);
        String countryKey=String.format("countryId",countryId);
        /*redisTemplate.opsForValue().set(countryKey,country);*/
        redisUtils.set(countryKey,country);
        /*return (Country) redisTemplate.opsForValue().get(countryKey);*/
        return (Country) redisUtils.get(countryKey);
    }
}
