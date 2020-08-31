package com.xiang.springcloud.springCloudClientAccount.modules.account.service.impl;

import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xiang.springcloud.springCloudClientAccount.modules.account.dao.UserDao;
import com.xiang.springcloud.springCloudClientAccount.modules.account.entity.City;
import com.xiang.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.xiang.springcloud.springCloudClientAccount.modules.account.service.TestFeignClient;
import com.xiang.springcloud.springCloudClientAccount.modules.account.service.UserSerivce;
import com.xiang.springcloud.springCloudClientAccount.modules.common.vo.Result;
import com.xiang.springcloud.springCloudClientAccount.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName UserSerivceImpl.java
 * @Description TODO
 * @createTime 2020年08月20日 13:35:00
 */
@Service
public class UserSerivceImpl implements UserSerivce {
   @Autowired
   private UserDao userDao;

   @Autowired
   private RestTemplate restTemplate;

    @Autowired
   private TestFeignClient  testFeignClient;


    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        if (userDao.selectUserByUserName(user.getUserName())!=null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"user name is repeat");
        }
        user.setCreateDate(LocalDateTime.now());
        /*user.setPassword(MD5Util.getMD5(user.getPassword()));*/
        userDao.insertUser(user);
     /*   userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            *//*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*//*
            //JDK8版本
            roles.stream().forEach(item ->{
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }*/
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"insert success",user);
    }

    @Override
    public  Result<User> login(User user) {
     /*   Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(user.getAccountName(),MD5Util.getMD5(user.getPassword()));
        try {
            subject.login(usernamePasswordToken);
            subject.checkRoles();
        }catch (Exception e){
            e.printStackTrace();
            return new Result<User>(Result.ResultStatus.FAILD.status,"Username or password error");
        }
        Session session = subject.getSession();
        session.setAttribute("user",(User)subject.getPrincipal());*/
        return  new Result<User>(Result.ResultStatus.SUCCESS.status,"login success",user);
    }

    @Override
    public PageInfo<User> selectUsersBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<User>(Optional.ofNullable(userDao.selectUsersBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }

    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp=userDao.selectUserByUserName(user.getUserName());
        if (userTemp!=null && userTemp.getUserId()!=user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status,"user name is repeat");
        }
        userDao.updateUser(user);
    /*    userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles=user.getRoles();
        if (roles!=null && !roles.isEmpty()){
            *//*for (Role role : roles) {
                userRoleDao.insertUserRole(user.getUserId(),role.getRoleId());
            }*//*
            //JDK8版本
            roles.stream().forEach(item ->{
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }*/
        return new Result<>(Result.ResultStatus.SUCCESS.status,"update success");
    }

    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);

        return new Result<>(Result.ResultStatus.SUCCESS.status,"delete success");
    }

    @Override
    public User selectUserByUserId(int userId) {
        User user=userDao.selectUserByUserId(userId);
        if (user == null ){
            return null;
        }
        //List<City> cities=Optional.ofNullable(restTemplate.getForObject("http://CLIENT-TEST/city/cities/{countryId}",List.class,522)).orElse(Collections.emptyList());
        List<City> cities=testFeignClient.selectCitiesByCountryId(522);
        user.setCities(cities);
        return user;
    }


    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
/*
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Upload failed.");
        }
*/

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "Upload success.", null);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp=userDao.selectUserByUserName(user.getUserName());
        if (userTemp!=null && userTemp.getUserId()!=user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status,"user name is repeat");
        }
        userDao.updateUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"update success",user);
    }

    @Override
    public User selectUserByUserName(String userName) {
        return userDao.selectUserByUserName(userName);
    }

/*    @Override
    public void logout() {
        Subject subject= SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        session.setAttribute("user",null);
    }*/


}
