package com.xiang.springboot01.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RequestParamFilter.java
 * @Description TODO
 * @createTime 2020年08月17日 11:14:00
 */
@WebFilter(filterName = "RequestParamFilter",urlPatterns = "/**")
public class RequestParamFilter implements Filter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.debug("---------------init----------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("----------doFilter-----");
        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        /*Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        parameterMap.put("paramKey",new String[]{"***"});
        */
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(httpServletRequest){
            @Override
            public String getParameter(String name) {
                String value=httpServletRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuuuuk","***");
                }
                return super.getParameter(name);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] parameterValues = httpServletRequest.getParameterValues(name);
                if ( parameterValues!=null && parameterValues.length>0 ) {
                    for (int i = 0; i < parameterValues.length; i++) {
                        parameterValues[i] = parameterValues[i].replaceAll("fuuuuk", "***");
                    }
                    return parameterValues;
                }
               return super.getParameterValues(name);
            }
        };

       chain.doFilter(wrapper,response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("----------- destroy-----------");
    }
}
