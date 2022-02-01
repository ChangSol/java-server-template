package org.changsol.api.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Config Class
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //view 포워딩
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //필요한 포워딩이 있다면 작성
//        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
