package com.pjq.config;


import com.pjq.filter.CorssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFilterConfigurerAdpater {

    @Bean
    public CorssFilter crossFilter() {
        return new CorssFilter();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 注入过滤器
        registration.setFilter(crossFilter());
        // 拦截规则
        registration.addUrlPatterns("*");
        // 过滤器名称
        registration.setName("CORSFilter");
        // 是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(true);
        // 过滤器顺序
        registration.setOrder(0);
        return registration;

    }


}
