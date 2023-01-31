package org.changsol.api.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * CommonConfig Class
 */
@Configuration
@EnableJpaAuditing //Audit 사용
@SuppressWarnings("unused")
public class CommonConfig {
}
