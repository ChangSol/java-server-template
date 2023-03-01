package org.changsol.api.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Config Class
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RestTemplateBuilder restTemplateBuilder;

    //view 포워딩
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //필요한 포워딩이 있다면 작성
//        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeviceResolverHandlerInterceptor());
    }
}
