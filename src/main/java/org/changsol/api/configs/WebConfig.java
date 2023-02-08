package org.changsol.api.configs;

import lombok.RequiredArgsConstructor;
import org.changsol.api.security.support.RestAuthenticationEntryPoint;
import org.changsol.api.security.support.RestAuthenticationFailureHandler;
import org.changsol.api.security.support.RestAuthenticationSuccessHandler;
import org.changsol.api.security.support.RestLogoutSuccessHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
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
