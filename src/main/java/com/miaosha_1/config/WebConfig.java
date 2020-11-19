package com.miaosha_1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author qqtang
 * @Date 2020/11/18 17:03
 * @Desc
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    UserArgumentResolvers userArgumentResolvers;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolvers);
    }
}
