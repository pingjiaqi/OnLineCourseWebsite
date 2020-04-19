package com.pjq.config;

import com.pjq.interceptor.UserNameInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 13457
 */
@Configuration
@EnableWebMvc
//拦截器
public class WebInterceptorConfigurerAdpater extends WebMvcConfigurerAdapter {

    @Autowired
    private UserNameInterceptor userNameInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userNameInterceptor)
                .addPathPatterns("/api/course/my/**")
                .addPathPatterns("/api/post/my/**")
                .addPathPatterns("/api/shoppingcart/**")
                .addPathPatterns("/api/time")
                .addPathPatterns("/user/showinformation")
                .addPathPatterns("/player")
                .addPathPatterns("/notes")
                .addPathPatterns("/api/file/**");
//                .excludePathPatterns("/user/**");
    }
}
