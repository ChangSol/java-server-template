package org.changsol.api.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Config Class
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfig implements WebMvcConfigurer {

    private final RestTemplateBuilder restTemplateBuilder;

    /**
     * PasswordEncoder 빈 정의
     */
    @Bean
    @ConditionalOnMissingBean // 오버라이딩 충돌 해결을 위함 동일 Bean 정의 시 해당 Bean 사용 X
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * RestTemplate 빈 정의
     */
    @Bean
    @ConditionalOnMissingBean // 오버라이딩 충돌 해결을 위함 동일 Bean 정의 시 해당 Bean 사용 X
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

}
