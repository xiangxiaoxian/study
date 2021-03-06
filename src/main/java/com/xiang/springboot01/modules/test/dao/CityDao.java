package com.xiang.springboot01.modules.test.dao;

import com.xiang.springboot01.modules.common.vo.SearchVo;
import com.xiang.springboot01.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CityDao.java
 * @Description TODO
 * @createTime 2020年08月11日 16:21:00
 */

@Repository
@Mapper
public interface CityDao {


    @Select("select * from m_city where country_id = #{countryId}")
    List<City> selectCityByCountryId(int countryId);

    @Select("<script>" +
            "select * from m_city "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by city_id desc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<City> selectCitiesBySearchVo(SearchVo searchVo);

    @Insert("insert into m_city (city_name, local_city_name, country_id, date_created) " +
            "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
    @Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
    void insertCity(City city);


    @Update("update  m_city set city_name = #{cityName} where city_id = #{cityId}")
    void updateCity(City city);


    @Delete("delete from m_city where city_id = #{cityId}")
    void deleteCity(int cityId);
 }
