package com.xiang.springboot01.modules.test.dao;

import com.xiang.springboot01.modules.test.entity.Country;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiangxiaoxian
 * @version 1.0.0
 * @ClassName CountryDao.java
 * @Description TODO
 * @createTime 2020年08月11日 16:22:00
 */
@Repository
@Mapper
public interface CountryDao {
    @Select("select * from m_country where country_id = #{countryId}")
    @Results(id = "countryResults",value = {
            @Result(column = "coubtry_id",property = "coubtryId"),
            @Result(column = "country_id", property = "cities",
                    javaType = List.class,
                    many = @Many(select =
                            "com.xiang.springboot01.modules.test.dao.CityDao.selectCityByCountryId"))
    })
    Country selectCountryByCountryId(int countryId);

    @Select("select * from m_country where country_name = #{countryName}")
    @ResultMap(value = "countryResults")
    Country selectCountryByCountryName(String countryName);
}
