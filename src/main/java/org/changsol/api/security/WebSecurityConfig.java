package org.changsol.api.security;

import lombok.RequiredArgsConstructor;
import org.changsol.api.security.support.RestAuthenticationEntryPoint;
import org.changsol.api.security.support.RestAuthenticationFailureHandler;
import org.changsol.api.security.support.RestAuthenticationSuccessHandler;
import org.changsol.api.security.support.RestLogoutSuccessHandler;
import org.changsol.api.security.users.UserAuthenticationProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserAuthenticationProvider userAuthenticationProvider;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    private final RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    private final RestLogoutSuccessHandler restLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Bean
    @ConditionalOnMissingBean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/docs/index.html",
                "/vendors/**",
                "/swagger-ui/",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/webjars/**",
                "/fonts/**");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");

        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("OPTIONS");

        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("*");

        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/oauth/social/**", "/oauth2/callback", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
                .antMatchers("/www/**").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/h2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .formLogin()
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .and()
                .csrf().disable()
                .cors();
    }
}
