package com.pjq.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class TimeOutConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("1GB");
//        factory.setMaxRequestSize("1GB");
//        String location = System.getProperty("user.dir") + "/data/tmp";
//        File tmpFile = new File(location);
//        if (!tmpFile.exists()) {
//            tmpFile.mkdirs();
//        }
//        factory.setLocation(location);
        return factory.createMultipartConfig();
    }
}
