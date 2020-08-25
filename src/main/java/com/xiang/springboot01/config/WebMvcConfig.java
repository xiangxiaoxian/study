package com.xiang.springboot01.config;

import com.xiang.springboot01.filter.RequestParamFilter;
import com.xiang.springboot01.interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.swing.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName WebMvcConfig.java
 * @Description TODO
 * @createTime 2020年08月11日 11:32:00
 */
@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${server.http.port}")
    private int httpPort;
    @Autowired
    private RequestViewInterceptor requestViewInterceptor;

    @Autowired
    private ResourceConfigBean resourceConfigBean;

    @Bean
    public Connector connector(){
        Connector connector=new Connector();
        connector.setPort(httpPort);
        connector.setScheme("http");
        return connector;
    }


    @Bean
    public ServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(connector());
        return tomcat;
    }

    @Bean
    public FilterRegistrationBean<RequestParamFilter> register(){
        FilterRegistrationBean<RequestParamFilter> regist=new FilterRegistrationBean<RequestParamFilter>();
        regist.setFilter(new RequestParamFilter());
        return regist;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String osName=System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern()).addResourceLocations(ResourceUtils.FILE_URL_PREFIX + resourceConfigBean.getLocationPathForWindows());
        }else {
            registry.addResourceHandler(resourceConfigBean.getRelativePathPattern()).addResourceLocations(ResourceUtils.FILE_URL_PREFIX+resourceConfigBean.getLocationPathForLinux());
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("wellcome");
    }
}
