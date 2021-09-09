package com.example.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerCfg extends ResourceServerConfigurerAdapter {

    @Autowired private TokenStore tokenStore;

    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers(
                    "/webjars/**",
                    "/swagger-ui/**", "/swagger-ui.html/**", "/v3/api-docs/**",
                    "/swagger-resources/**", "/error**",
                    "/actuator/**")
                .permitAll()
            .antMatchers("/api/**")
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .tokenStore(tokenStore)
                .resourceId(resourceId);
    }
}
