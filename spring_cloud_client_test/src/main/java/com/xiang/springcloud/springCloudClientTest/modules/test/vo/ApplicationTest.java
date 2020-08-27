package com.xiang.springcloud.springCloudClientTest.modules.test.vo;

import org.springframework.stereotype.Component;

/**
 * @author xiangxiaoxian
 * @version 1.0.0
 * @ClassName ApplicationTest.java
 * @Description TODO
 * @createTime 2020年08月10日 18:36:00
 */
@Component
/*@PropertySource("classpath:config/applicationTest.properties")
@ConfigurationProperties(prefix = "com.xiang")*/
public class ApplicationTest {
    private String port;
    private String name;
    private String age;
    private String sex;
    private String random;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
