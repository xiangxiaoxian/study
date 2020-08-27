package com.xiang.springcloud.springCloudRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRegisterApplication.class, args);
    }

}
